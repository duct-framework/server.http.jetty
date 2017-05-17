# Duct server.http.jetty

[![Build Status](https://travis-ci.org/duct-framework/server.http.jetty.svg?branch=master)](https://travis-ci.org/duct-framework/server.http.jetty)

Integrant multimethods for running a [Jetty][] server for the [Duct][]
framework.

[jetty]: https://eclipse.org/jetty/
[duct]: https://github.com/duct-framework/duct

## Installation

To install, add the following to your project `:dependencies`:

    [duct/server.http.jetty "0.1.4"]

## Usage

The multimethods dispatch off the `:duct.server.http/jetty` key, which
is derived from `:duct.server/http`. The corresponding value is a map
of options for the [Ring Jetty Adapter][], plus a `:handler` key that
takes a handler function.

For example:

```clojure
{:duct.server.http/jetty
 {:port    3000
  :handler (fn [request]
             {:status  200
              :headers {"Content-Type" "text/plain"}
              :body    "Hello World"})}}
```

A `:logger` key may also be specified, which will be used to log when
the server starts and when it stops. The value of the key should be an
implementation of the `duct.logger/Logger` protocol from the
[duct.logger][] library

[ring jetty adapter]: https://ring-clojure.github.io/ring/ring.adapter.jetty.html
[duct.logger]: https://github.com/duct-framework/logger

## License

Copyright © 2017 James Reeves

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
