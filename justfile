default:
    @just --list

# Start the auto-runner: it shows the next koan to meditate on and re-checks it on every save. Never exits.
run:
    clojure -M:koans run

# Meditate on random koans, answering at the prompt.
random:
    clojure -M:koans random

# Start a REPL with the koans on the classpath.
repl:
    clj -M:koans-repl

# Check the answer sheet: every koan fails while blank and passes once resources/koans.clj is filled in.
test:
    clojure -M:koans test

fmt:
    cljfmt fix src dev

# Lint the koans. Type errors on unfilled blanks (`__`, `___`) are expected.
lint:
    clj-kondo --lint "$(clojure -Spath)" --dependencies --parallel --copy-configs > /dev/null
    clj-kondo --lint src dev

clean:
    rm -rf .cpcache .clj-kondo/.cache .lsp
