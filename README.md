# Duct-Jetty-Server

Integrant multimethods for running a Jetty server.

## Installation

To install, add the following to your project `:dependencies`:

    [duct/jetty-server "0.1.0-SNAPSHOT"]

## Usage

The multimethods dispatch off the `:duct.server/jetty` key. The
corresponding value is a map of options for the
[Ring Jetty Adapter][], plus a `:handler` key that takes a handler
function.

For example:

```clojure
{:duct.server/jetty
 {:port    3000
  :handler (fn [request]
             {:status  200
              :headers {"Content-Type" "text/plain"}
              :body    "Hello World"})}}
```

[ring jetty adapter]: https://ring-clojure.github.io/ring/ring.adapter.jetty.html

## License

Copyright © 2016 James Reeves

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
