# lein-okapi

A Leiningen plugin to do many wonderful things with the Okapi
Translation and Localisation Framework.

# Status:  pre-alpha

## Prerequisites

You have to build and install Okapi locally, so that the Okapi jars
get put into the maven repo (~/.m2/repository) that leiningen uses.

## Usage

FIXME: Use this for user-level plugins:

Put `[lein-okapi "0.1.0-SNAPSHOT"]` into the `:plugins` vector of your
`:user` profile, or if you are on Leiningen 1.x do `lein plugin install
lein-okapi 0.1.0-SNAPSHOT`.

FIXME: Use this for project-level plugins:

Put `[lein-okapi "0.1.0-SNAPSHOT"]` into the `:plugins` vector of your project.clj.

Only the "configs" command is currently implemented.  It shows all available filter configurations, like tikal's -lcf.

    $ lein okapi configs

## License

Copyright © 2013 Gregg Reynolds

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
