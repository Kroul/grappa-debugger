package com.github.fge.grappa.debugger.tracetab;

import com.github.fge.grappa.buffers.InputBuffer;
import com.github.fge.grappa.debugger.legacy.InputTextInfo;
import com.github.fge.grappa.debugger.legacy.ParsingRunTrace;
import com.github.fge.grappa.debugger.legacy.RuleStatistics;
import com.github.fge.grappa.debugger.legacy.TraceEvent;
import com.github.fge.grappa.debugger.tracetab.statistics.ParseNode;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;

public interface TraceTabModel
{
    @Nonnull
    ParsingRunTrace getTrace();

    @Nonnull
    InputBuffer getInputBuffer();

    @Nonnull
    List<TraceEvent> getTraceEvents();

    @Nonnull
    InputTextInfo getInputTextInfo();

    @Nonnull
    Collection<RuleStatistics> getRuleStats();

    @Nonnull
    ParseNode getParseTreeRoot();
}
