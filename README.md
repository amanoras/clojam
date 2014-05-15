# Clojam

A Clojure library for solving Google Code Jam problems.

## Code Jam

[Code Jam](https://code.google.com/codejam) is Google's annual coding competition. It consists of a series of rounds and in each round a series of problems that must be solved in a limited time. The problems usually consist of reading data from an input file and processing it in some way o get the required results with points being awarded for completing the problem for small and large datasets as well as how quickly the problem is solved.

I wanted to try solving the problems using Clojure and while working through the prior years problems I put together this library to handle the mundane stuff like reading the input and writing the output so I could focus on solving the actual problem.

## Usage
So how do you use it ?

First you need to include the library by adding the following to your project.clj:

```
[amanoras/clojam "0.1.0"]
```

Next you need to include the library in your code, for example:

```
(:use [clojam core cases utils])
```

Finaly you should (but you don't have to) define a main function that can be called from the command line e.g. using lein run, so that you can pass in the names of your input and output files. The core of the library is the jam function which takes as arguments:
- the path to the input file
- a vector that describes how the data in the input file should be combined into cases
- a function that solves a case
- a function that prints the output of a case in the correct format
- the path to the output file

For example:

```
(defn -main [infile outfile & args]
  (jam infile [:param1 :param2 :param3]
     solve-case
     output-format outfile))
```

In this example the data in the input file should be grouped into cases 3 lines at a time, but sometimes the input file can contain several fixed lines before the cases start. In this case you can pass a nested vector as the last element of the vector describing the file structure. This is best illustrated with an example:

```
(defn -main [infile outfile & args]
  (jam infile [:param1 [:param2 :param3]]
     solve-case
     output-format outfile))
```

In this example we're specifying that the data file contains 1 fixed line and then each case consists of 2 lines of data.

It should be noted that this format vector ignores the first line of the data file as this always contains the number of cases in the file.

## Samples

To get an idea of how the library works I've included some example code in the samples folder. These are the solutions to the problems for round 1A in 2008. You can see the problem descriptions [here](https://code.google.com/codejam/contest/32016/dashboard).

## Disclaimer

I'm not really maintaining this library as such as my main aim in building it was to learn Clojure, but if you find it useful feel free to fork it and work away.

## License

Copyright © 2013 Anthony Manoras

Distributed under the Eclipse Public License, the same as Clojure.
