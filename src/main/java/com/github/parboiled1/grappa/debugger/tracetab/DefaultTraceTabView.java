package com.github.parboiled1.grappa.debugger.tracetab;

import com.github.parboiled1.grappa.debugger.tracetab.statistics.RuleStatistics;
import com.github.parboiled1.grappa.trace.TraceEvent;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Collection;
import java.util.List;

public final class DefaultTraceTabView
    implements TraceTabView
{
    private final TraceTabUi ui;

    public DefaultTraceTabView(final TraceTabUi ui)
    {
        this.ui = ui;

        /*
         * Trace events
         */
        bindColumn(ui.eventTime, "nanoseconds");
        setDisplayNanos(ui.eventTime);
        bindColumn(ui.eventDepth, "level");
        bindColumn(ui.eventIndex, "index");
        bindColumn(ui.eventPath, "path");
        bindColumn(ui.eventRule, "matcher");
        bindColumn(ui.eventType, "type");

        /*
         * Statistics
         */
        bindColumn(ui.statsRule, "ruleName");
        bindColumn(ui.statsInvocations, "nrInvocations");
        bindColumn(ui.statsSuccess, "nrSuccesses");
        ui.statsSuccessRate.setCellValueFactory(
            param -> new SimpleObjectProperty<Double>()
            {
                @SuppressWarnings("AutoBoxing")
                @Override
                public Double get()
                {
                    final RuleStatistics stats = param.getValue();
                    //noinspection AutoBoxing
                    return 100.0 * stats.getNrSuccesses()
                        / stats.getNrInvocations();
                }
            });
        ui.statsSuccessRate.setCellFactory(
            param -> new TableCell<RuleStatistics, Double>()
            {
                @Override
                protected void updateItem(final Double item,
                    final boolean empty)
                {
                    super.updateItem(item, empty);
                    setText(empty ? null : String.format("%.2f%%", item));
                }
            });
        bindColumn(ui.statsTotalTime, "totalTime");
        setDisplayNanos(ui.statsTotalTime);
        ui.statsAvgTime.setCellValueFactory(
            param -> new SimpleObjectProperty<Long>()
            {
                @SuppressWarnings("AutoBoxing")
                @Override
                public Long get()
                {
                    final RuleStatistics stats = param.getValue();
                    return stats.getTotalTime() / stats.getNrInvocations();
                }
            }
        );
        setDisplayNanos(ui.statsAvgTime);
    }

    @Override
    public void setTraceEvents(final List<TraceEvent> events)
    {
        ui.events.getItems().setAll(events);
        final Tab tab = ui.eventsTab;
        @SuppressWarnings("AutoBoxing")
        final String newText
            = String.format("%s (%d)", tab.getText(), events.size());
        tab.setText(newText);
    }

    @Override
    public void setStatistics(final Collection<RuleStatistics> values)
    {
        ui.stats.getItems().setAll(values);
    }

    private static <S, T> void bindColumn(final TableColumn<S, T> column,
        final String propertyName)
    {
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
    }

    private static <S> void setDisplayNanos(final TableColumn<S, Long> column)
    {
        column.setCellFactory(param -> new TableCell<S, Long>()
        {
            @Override
            protected void updateItem(final Long item, final boolean empty)
            {
                super.updateItem(item, empty);
                //noinspection AutoUnboxing
                setText(empty ? null : nanosToText(item));
            }
        });
    }

    @SuppressWarnings("AutoBoxing")
    private static String nanosToText(final long nanos)
    {
        long value = nanos;
        final long nrNanoseconds = value % 1000;
        value /= 1000;
        final long nrMicroseconds = value % 1000;
        value /= 1000;

        return String.format("%d ms, %03d.%03d µs", value, nrMicroseconds,
            nrNanoseconds);
    }
}
