# This file is responsible for configuring your application
# and its dependencies with the aid of the Mix.Config module.
#
# This configuration file is loaded before any dependency and
# is restricted to this project.

# General application configuration
use Mix.Config

config :my_app,
  ecto_repos: [MyApp.Repo]

# Configures the endpoint
config :my_app, MyAppWeb.Endpoint,
  url: [host: "localhost"],
  secret_key_base: "d/aLwe/ezmRD9Fl58XOQBOkwvlPrR4RZfRPn7TSBV0/Bxm0q5wfXgVOXFQ8k0Fdc",
  render_errors: [view: MyAppWeb.ErrorView, accepts: ~w(json)],
  pubsub: [name: MyApp.PubSub, adapter: Phoenix.PubSub.PG2]

# Configures Elixir's Logger
config :logger, :console,
  format: "$time $metadata[$level] $message\n",
  metadata: [:request_id]

# Use Jason for JSON parsing in Phoenix
config :phoenix, :json_library, Jason

config :exldap, :settings,
  server: "ldap.forumsys.com",
  base: "dc=example,dc=com",
  port: 389,
  ssl: true,
  user_dn: "cn=read-only-admin,dc=example,dc=com",
  password: "password",
  search_timeout: 1000
# Import environment specific config. This must remain at the bottom
# of this file so it overrides the configuration defined above.
import_config "#{Mix.env()}.exs"
