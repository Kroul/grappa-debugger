package com.github.fge.grappa.debugger.csvtrace;

import com.github.fge.grappa.debugger.GrappaDebuggerException;
import com.github.fge.grappa.debugger.common.GuiTaskRunner;
import com.github.fge.grappa.debugger.csvtrace.tabs.matches.MatchesTabPresenter;
import com.github.fge.grappa.debugger.csvtrace.tabs.rules.RulesTabPresenter;
import com.github.fge.grappa.debugger.csvtrace.tabs.tree.TreeTabPresenter;
import com.github.fge.grappa.debugger.csvtrace.tabs.treedepth.TreeDepthTabPresenter;
import com.github.fge.grappa.debugger.javafx.BasePresenter;
import com.github.fge.grappa.debugger.mainwindow.MainWindowView;
import com.github.fge.grappa.internal.NonFinalForTesting;
import com.google.common.annotations.VisibleForTesting;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

@NonFinalForTesting
@ParametersAreNonnullByDefault
public class CsvTracePresenter
    extends BasePresenter<CsvTraceView>
{
    private final MainWindowView mainView;
    private final GuiTaskRunner taskRunner;
    private final CsvTraceModel model;



    public CsvTracePresenter(final MainWindowView mainView,
        final GuiTaskRunner taskRunner, final CsvTraceModel model)
    {
        this.mainView = Objects.requireNonNull(mainView);
        this.taskRunner = taskRunner;
        this.model = Objects.requireNonNull(model);
    }

    @Override
    public void load()
    {
        loadTreeTab();
        loadRulesTab();
        loadMatchesTab();
        loadTreeDepthTab();
    }

    @VisibleForTesting
    void loadTreeTab()
    {
        final TreeTabPresenter treeTab = createTreeTabPresenter();
        view.loadTreeTab(treeTab);
        treeTab.load();
    }

    @VisibleForTesting
    TreeTabPresenter createTreeTabPresenter()
    {
        return new TreeTabPresenter(taskRunner, mainView, model);
    }

    @VisibleForTesting
    void loadRulesTab()
    {
        final RulesTabPresenter rulesTab = createRulesTabPresenter();
        view.loadRulesTab(rulesTab);
        rulesTab.load();
    }

    @VisibleForTesting
    RulesTabPresenter createRulesTabPresenter()
    {
        return new RulesTabPresenter(taskRunner, mainView, model);
    }

    @VisibleForTesting
    void loadMatchesTab()
    {
        final MatchesTabPresenter matchesTab = createMatchesTabPresenter();
        view.loadMatchesTab(matchesTab);
        matchesTab.load();
    }

    @VisibleForTesting
    MatchesTabPresenter createMatchesTabPresenter()
    {
        return new MatchesTabPresenter(taskRunner, model, mainView);
    }

    // UNUSED...
    @VisibleForTesting
    void loadTreeDepthTab()
    {
        final TreeDepthTabPresenter treeDepthTab
            = createTreeDepthTabPresenter();
        view.loadTreeDepthTab(treeDepthTab);
        treeDepthTab.load();
    }

    @VisibleForTesting
    TreeDepthTabPresenter createTreeDepthTabPresenter()
    {
        return new TreeDepthTabPresenter(taskRunner, mainView, model);
    }

    public void dispose()
    {
        try {
            model.dispose();
        } catch (GrappaDebuggerException e) {
            mainView.showError("Trace file error", "Problem closing trace file",
                e);
        }
    }

    public void handleTabsRefreshEvent()
    {
        // TODO

    }
}
