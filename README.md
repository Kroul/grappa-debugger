## Read me first

This project is licensed under both LGPLv3 and ASL 2.0. See file LICENSE for more details.

This project has been developed with the latest Oracle JDK, that is 1.8u25. It requires JavaFX 8.

The current version is **0.1.0**. You can download a self contained jar with a main on
[Bintray](https://bintray.com/fge/maven/grappa-debugger/view).

## What this is

This is a GUI application to debug parsers written with
[grappa](https://github.com/parboiled1/grappa).

It uses trace files generated by a parser runner originally developed for the next grappa iteration,
2.0.x. However, the parsing runner used to generate the trace files has been
[backported](https://github.com/fge/grappa-tracer-backport) to grappa 1.0.x and, by extension,
parboiled 1.1.x as well.

For those among you in the know: **this tracing parser does NOT require that parser classes be
annotated with `@BuildParseTree` in order to produce its trace files**.

## Features

The tracer will collect two elements (the input text and a list of tracing events) into a zip file.
This application will load such a zip file and will offer you several means of analyzing the parsing
process.

For more details, see [this wiki
page](https://github.com/fge/grappa-debugger/wiki/Quick-overview).

Future plans include:

* running a live debugging session of a parsing run;
* decompiling generated parsers.

