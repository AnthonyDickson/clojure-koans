# Clojure Koans

The Clojure Koans are a fun way to get started with Clojure - no experience assumed or required. Follow the instructions
below to start making tests pass!

This fork runs the koans with the [Clojure CLI](https://clojure.org/guides/deps_and_cli) and a
[`deps.edn`](https://clojure.org/reference/deps_edn) project on Clojure 1.12 and JDK 25, instead of Leiningen, and adds
koans for what the language has gained since 1.10 (see [New in Clojure 1.11 and 1.12](#new-in-clojure-111-and-112)).

## Getting Started

I recommend starting from a cloned or forked repo. This way you'll be able to track your progress in Git. You might want
to create your own branch - that way if you pull back the latest koans from master, it'll be a bit easier to manage the
inevitable conflicts if we make changes to exercises you've already completed.

The [flake.nix](./flake.nix) provides everything needed to run the koans: the Clojure CLI on JDK 25, `just`, the
`clojure-lsp` language server, `clj-kondo` for linting, `cljfmt` for formatting, and `dprint` for markdown. If you have
`nix`, enter the dev shell with:

```shell
nix develop
```

The Clojure CLI downloads Clojure and the koan engine on the first run and caches them under `~/.m2`.

## Useful Commands

`just` lists every recipe.

- Meditate on the koans, in order:

  ```shell
  just run
  ```

- Meditate on random koans, answering at the prompt:

  ```shell
  just random
  ```

- Start a REPL:

  ```shell
  just repl
  ```

- Format your answers with `cljfmt`, then lint them with `clj-kondo`:

  ```shell
  just fmt
  just lint
  ```

- Check the answer sheet, for when you write koans of your own:

  ```shell
  just test
  ```

## Running the Koans

`just run` is an auto-runner, so as you save your files with the correct answers, it will advance you to the next koan
or file (conveniently, all files are prefixed with the sequence that you should follow). It never exits; stop it with
`CTRL-c`.

You'll see something like this:

```
Now meditate upon 01_equalities.clj:6
---------------------
Assertion failed!
We shall contemplate truth by testing reality, via equality
(= __ true)
```

The output is telling you that you have a failing test in the file named `01_equalities.clj`, on line 6. So you need to
open that file up and make it pass! You'll always be filling in the blanks to make tests pass. Sometimes there could be
several correct answers (or even an infinite number): any of them will work in these cases. Some tests will pass even if
you replace the blanks with whitespace (or nothing) instead of the expected answer. Make sure you give one correct
expression to replace each blank. A `__` is a blank for a value, and a `___` is a blank for a function.

The koans differ from normal TDD in that the tests are already written for you, so you'll have to pay close attention to
the failure messages, because up until the very end, making a test pass means that the next failure message comes up.

While it might be easy (especially at first) to fill in the blanks making things pass, you should work thoughtfully,
making sure you understand why the answer is what it is. Enjoy your path to Clojure enlightenment!

## Trying more things out

It's very useful to try things out in a REPL (Read-Evaluate-Print Loop) whenever you get stuck or curious. Run `just
repl` and you'll be able to type expressions in, and see what output they produce. `meditations`, `__` and `___` are
available there too, so you can paste a koan in whole.

Here are some interesting commands you might try, once you're in a running REPL:

```clojure
(find-doc "vec")
(find-doc #"vec$")
(doc vec)
(source vec)
```

And if those still don't make sense:

```clojure
(doc doc)
(doc find-doc)
```

will show you what those commands mean.

Since Clojure 1.12 you can also pull a library into the running REPL without restarting it:

```clojure
(add-lib 'org.clojure/data.json)
(require '[clojure.data.json :as json])
(json/write-str {:hello "world"})
```

You can exit the REPL with `CTRL-d`.

## New in Clojure 1.11 and 1.12

The original koans stop at Clojure 1.10. Koans 28 to 34 cover what has been added since:

| Koan                     | Version | Covers                                                                                                                 |
| ------------------------ | ------- | ---------------------------------------------------------------------------------------------------------------------- |
| `28_parsing_and_math`    | 1.11    | `parse-long`, `parse-double`, `parse-boolean`, `parse-uuid`, `random-uuid`, `abs`, `NaN?`, `infinite?`, `clojure.math` |
| `29_updating_maps`       | 1.11    | `update-keys`, `update-vals`                                                                                           |
| `30_keyword_arguments`   | 1.11    | Keyword-argument functions that accept a trailing map, `:as-alias` in `require`                                        |
| `31_iteration`           | 1.11    | `iteration`, for consuming paginated APIs                                                                              |
| `32_vector_partitions`   | 1.12    | `partitionv`, `partitionv-all`, `splitv-at`                                                                            |
| `33_modern_java_interop` | 1.12    | Qualified methods as values, `^[...]` param tags, array class syntax, functional interfaces, `IDeref` as a `Supplier`  |
| `34_java_streams`        | 1.12    | `stream-seq!`, `stream-reduce!`, `stream-into!`, `stream-transduce!`                                                   |

Clojure 1.12 also added `add-lib` (see above) and `clojure.java.process` for running external programs, which are better
tried in the REPL than as koans.

## Contributing

Make sure the answer sheet still passes (`just test`) after any change. To write a new koan, add the file to `src/koans`
and its answers, in the order the blanks appear, to `resources/koans.clj`.

The file ideaboard.txt has lots of good ideas for new koans to start, or things to add to existing koans.

## How this fork runs the koans

The koans use the [koan-engine](https://github.com/functional-koans/koan-engine) library, whose entry point reads its
settings from a Leiningen `project.clj`. [dev/runner.clj](./dev/runner.clj) calls the engine's run, test, and random
tasks with the same default settings instead, so the koans run from `deps.edn`.

## Contributors

https://github.com/functional-koans/clojure-koans/contributors

## Credits

These exercises were started by [Aaron Bedra](http://github.com/abedra) of
[Relevance, Inc.](http://github.com/relevance) in early 2010, as a learning tool for newcomers to functional
programming. Aaron's macro-fu makes these koans clear and fun to use and improve upon, and without Relevance's
initiative, this project would not exist.

Using the [koans](http://en.wikipedia.org/wiki/koan) metaphor as a tool for learning a programming language started with
the [Ruby Koans](http://rubykoans.com) by [EdgeCase](http://github.com/edgecase).

## License

The use and distribution terms for this software are covered by the Eclipse Public License 1.0
(http://opensource.org/licenses/eclipse-1.0.php) which can be found in the file epl-v10.html at the root of this
distribution. By using this software in any fashion, you are agreeing to be bound by the terms of this license.
