SKWEEKParser
============

This is a re-work of the parser included in the SKWEEK project. I'm attempting
to make a better implementation by better design and refactoring.

Anyone who comes across this project and wants to lend a hand, feel free to do 
so!

This parser is more or less a version of a standard math expression parser. It 
is used in the SKWEEK app, which takes a math-like expression and turns it into
8-bit sound sequence (what's knows as bytebeat). The parser is capable of 
handling variables internally hardwired to "t", "x", "y", and "z". A client can
build an instance of `Parser` with the choice of passing values that correspond 
to this variables.

Internally, the `Parser` class splits a `String` of the expression and splits 
it into a list of "symbols". This list is then processed to be converted to 
"postfix" list of these symbols, and finally, the "postfix" list is read and 
calculated into the final result.

Although the project includes classes that represent "symbols" (inside the 
`com.rburgos.skweekparser.symbols` package), these classes are not yet 
implemented within `Parser` but ideally they will.
