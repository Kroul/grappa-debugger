package com.github.fge.grappa.debugger.legacy.tracetab;

import com.github.fge.grappa.buffers.InputBuffer;
import com.github.fge.grappa.debugger.legacy.LegacyTraceEvent;
import com.github.fge.grappa.debugger.statistics.ParseNode;

import java.util.List;

public class LegacyTraceTabPresenter
{
    private final LegacyTraceTabModel model;
    private final InputBuffer buffer;

    private LegacyTraceTabView view;

    public LegacyTraceTabPresenter(final LegacyTraceTabModel model)
    {
        this.model = model;
        buffer = model.getInputBuffer();
    }

    public void setView(final LegacyTraceTabView view)
    {
        this.view = view;
    }

    public void loadTrace()
    {
        final List<LegacyTraceEvent> events = model.getTraceEvents();

        view.setParseDate(model.getTrace().getStartDate());
        view.setTraceEvents(events);
        view.setStatistics(model.getRuleStats(), model.getNrEmptyMatches());
        view.setInputBuffer(buffer);
        view.setParseTree(model.getParseTreeRoot());
    }

    void handleParseNodeShow(final ParseNode node)
    {
        final int length = buffer.length();
        final int start = Math.min(node.getStart(), length);
        final int end = Math.min(node.getEnd(), length);

        view.fillParseNodeDetails(node);
        if (node.isSuccess())
            view.highlightSuccess(start, end);
        else
            view.highlightFailure(end);
    }

    public void handleExpandParseTree()
    {
        view.expandParseTree();
    }
}
