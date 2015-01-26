package com.github.fge.grappa.debugger.tracetab.stat.global;

import com.github.fge.grappa.debugger.stats.global.GlobalParseInfo;
import com.github.fge.grappa.debugger.stats.global.RuleMatchingStats;
import com.github.fge.grappa.trace.ParseRunInfo;

import java.util.Collection;

public interface GlobalStatsView
{
    void loadStats(Collection<RuleMatchingStats> stats);

    void loadInfo(ParseRunInfo info, int totalMatches);

    void loadParseInfo(GlobalParseInfo info);
}
