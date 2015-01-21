package com.github.fge.grappa.debugger.tracetab;

import com.github.fge.grappa.debugger.legacy.InputTextInfo;
import com.github.fge.grappa.debugger.tracetab.statistics.ParseNode;
import com.github.fge.grappa.debugger.legacy.RuleStatistics;
import com.github.parboiled1.grappa.buffers.InputBuffer;
import com.github.parboiled1.grappa.trace.TraceEvent;
import org.parboiled.support.Position;

import java.util.Collection;
import java.util.List;

public interface TraceTabGuiController
{
    void setTraceEvents(List<TraceEvent> events);

    void setStatistics(Collection<RuleStatistics> values);

    void setParseDate(long startDate);

    void setInputTextInfo(InputTextInfo textInfo);

    void setInputText(String inputText);

    void setParseTree(ParseNode node);

    void fillParseNodeDetails(ParseNode node, InputBuffer buffer);

    void highlightText(List<String> fragments, Position position,
        boolean success);
}