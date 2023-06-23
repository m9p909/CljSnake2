(defproject cljsnake2 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ring/ring-core "1.8.2"]
                 [ring/ring-jetty-adapter "1.8.2"]
                 [metosin/reitit "0.7.0-alpha5"]
                 [metosin/muuntaja "0.6.8"]
                 [ring/ring-devel "1.6.3"]
                 [ring-logger "1.1.1"]
                 [org.clojure/core.async "1.6.673"]
                 [org.clojure/tools.logging "1.2.4"]
                 [org.clojure/data.json "2.4.0"]
                 [metosin/muuntaja-yaml "0.6.8"]]
  :ring {:handler cljsnake2.core/handler}
  :repl-options {:init-ns cljsnake2.core}
  :plugins [[cider/cider-nrepl "0.24.0"]
            [lein-ring "0.12.6"]
            [dev.weavejester/lein-cljfmt "0.10.5"]])
