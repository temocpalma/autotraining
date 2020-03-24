defmodule PlugExample do
  use Application
  require Logger

  def start(_type, _args) do
    children = [
     {Plug.Cowboy, scheme: :http, plug: PlugExample.HelloWorldPlug, options: [port: 4001]}
    ]

    opts = [strategy: :one_for_one, name: PlugExample.Supervisor]
    Logger.info("Started application")
    Supervisor.start_link(children, opts)
  end
end
