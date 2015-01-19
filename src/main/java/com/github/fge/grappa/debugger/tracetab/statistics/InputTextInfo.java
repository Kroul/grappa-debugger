package com.github.fge.grappa.debugger.tracetab.statistics;

import com.github.fge.grappa.debugger.internal.NonFinalForTesting;
import com.github.parboiled1.grappa.buffers.InputBuffer;

@NonFinalForTesting
public class InputTextInfo
{
    private final int nrLines;
    private final int nrChars;
    private final int nrCodePoints;

    public InputTextInfo(final InputBuffer buffer)
    {
        nrLines = buffer.getLineCount();
        nrChars = buffer.length();
        nrCodePoints = buffer.extract(0, buffer.length())
            .codePointCount(0, nrChars);
    }

    public int getNrLines()
    {
        return nrLines;
    }

    public int getNrChars()
    {
        return nrChars;
    }

    public int getNrCodePoints()
    {
        return nrCodePoints;
    }
}