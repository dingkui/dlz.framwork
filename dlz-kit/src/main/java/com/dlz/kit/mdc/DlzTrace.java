package com.dlz.kit.mdc;

import com.dlz.kit.util.id.UuidUtil;
import org.slf4j.MDC;

/**
 * 调用链路追踪工具类。
 *
 * <p>基于 SLF4J MDC（Mapped Diagnostic Context）实现 traceId 的生成与作用域管理，
 * 是 {@code TraceUtil} 的升级版：将「手动 set/clear」升级为基于 {@link AutoCloseable}
 * 的「作用域自动恢复」，可避免线程池复用场景下 traceId 串线残留。</p>
 *
 * <p>核心约定：MDC 的键统一为 {@link #MDC_KEY_DLZ_TRACE}，日志格式通过
 * {@code %X{dlz-trace}} 打印 traceId。所有方法返回的 {@link MdcContext} 必须通过
 * try-with-resources 方式关闭，退出作用域时自动恢复上一级 traceId（或清除）。</p>
 *
 * <p>典型用法（分层调用）：</p>
 * <pre>{@code
 * // 请求入口：新建独立 traceId（根链路/子链路）
 * try (MdcContext ctx = DlzTrace.segmentTrace()) {
 *     // 请求内部逻辑：沿用当前 traceId
 *     try (MdcContext ctx2 = DlzTrace.trace()) {
 *         // ...
 *     }
 * } // 退出时自动恢复上一级 traceId
 * }</pre>
 */
public final class DlzTrace {
    /**
     * MDC 中存储 traceId 的键名，日志配置通过 %X{dlz-trace} 引用。
     */
    public static final String MDC_KEY_DLZ_TRACE = "dlz-trace";

    private DlzTrace() {
    }

    /**
     * 开启一条全新的独立 trace 片段（子链路）。
     *
     * <p>无论当前线程是否已有 traceId，都会生成一个新的 traceId 并覆盖，退出作用域时
     * 自动恢复上一级 traceId（不影响父链路）。</p>
     *
     * <p>典型使用场景：</p>
     * <ul>
     *   <li><b>并行/异步任务</b>：线程池中每个子任务开独立片段，避免复用线程的 traceId 串线；</li>
     *   <li><b>批量逐条处理</b>：每条数据独立标识，出错时便于定位是哪一条；</li>
     *   <li><b>请求重试</b>：每次重试独立标识，便于对比区分。</li>
     * </ul>
     *
     * @return 作用域上下文，用 try-with-resources 包裹，退出时自动恢复
     */
    public static MdcContext segmentTrace() {
        return trace(null);
    }

    /**
     * 获取当前线程的 traceId，供链路内部沿用。
     *
     * <p>若当前线程已有 traceId 则直接沿用（不生成新值，性能最优）；
     * 若为空则新建一个。适用于链路中间节点，保证整条调用链 traceId 一致。</p>
     *
     * <p>典型使用场景：请求内部每个方法/每次线程执行时调用，只做轻量 {@code MDC.get}，
     * 高频调用无性能压力。</p>
     *
     * @return 作用域上下文，用 try-with-resources 包裹，退出时自动恢复
     */
    public static MdcContext trace() {
        return trace(getTraceId());
    }

    /**
     * 以指定 traceId 建立/接入链路。
     *
     * <p>将外部传入的 traceId（如 HTTP 请求头、MQ 消息头、上游系统下发的标识）注入 MDC，
     * 打通不同来源的链路追踪。若传入为 null 或空白串，则自动回退新建一个 traceId。</p>
     *
     * <p>典型使用场景：</p>
     * <ul>
     *   <li><b>入口网关/过滤器</b>：从请求头读取上游 traceId 并接入本系统，实现跨系统链路贯通；</li>
     *   <li><b>新旧体系对接</b>：将 {@code TraceUtil} 历史生成的 traceId 注入新体系，平滑迁移；</li>
     *   <li><b>MQ 消费</b>：从消息体中读取生产者 traceId 延续链路。</li>
     * </ul>
     *
     * @param trace 外部传入的 traceId，可为 null（null/空白时自动新建）
     * @return 作用域上下文，用 try-with-resources 包裹，退出时自动恢复
     */
    public static MdcContext trace(String trace) {
        String resolved = (trace == null || trace.trim().isEmpty()) ? UuidUtil.shortUuid() : trace;
        return MdcContext.open(MDC_KEY_DLZ_TRACE, resolved);
    }

    public static String getTraceId() {
        return MDC.get(MDC_KEY_DLZ_TRACE);
    }
}
