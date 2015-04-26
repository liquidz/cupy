# cupy

Generate a text file from a template file and data files.

## Usage

 * Generate text file from a template, and output to STDOUT.
```
java -jar target/cupy-standalone.jar [TEMPLATE FILE] [DATA FILE1] ... [DATA FILEn]
```

Data files are merged if you specify some data files like follows:
```clj
(marge data1 data2 ... data3)
```

## Template file

Cupy uses [cuma](https://github.com/liquidz/cuma) as template engine.

## Data file

Cupy supports following file formats as data.

 * edn
 * yaml
 * json

## License

Copyright (c) 2015 [uochan](https://twitter.com/uochan)

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
