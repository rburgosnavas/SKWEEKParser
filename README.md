SKWEEKParser
============

This is a re-work of the parser included in the SKWEEK project. I'm attempting
to make a better implementation by better design and refactoring.

Anyone who comes across this project and wants to lend a hand, feel free to do 
so!

Update
------

The parser is now split into three different classes:
* `Lexer` splits a `String` of an expression into a List of `Symbols`;
* `Evaluator` basically takes a List of `Symbols` and returns a new List of 
`Symbols` converted to postfix;
* `Interpreter` interprets this List and returns the result

The old README below explains the general purpose of the parser. The `Parser`
class was responsible for splitting to tokens, converting to postfix, and 
calculating the postfix expression and returning the result. These 
responsibilities are now divided by the three main classes listed above. As I'm 
still learning from this project, and as I expect bugs to creep out, I'm 
leaving the `Parser` class in the project for now. I feel that `Parser` works 
fairly well by itself so it won't hurt. For more info about `Parser`, read the 
old README below.

To amend the lack documentation, a `Runner` class is included to show how the 
`Parser` works, as well as the interaction between `Lexer`, `Evaluator`, and 
`Interpreter`. I do plan to fully document everything once I know everything 
works well.

Old README
----------

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
