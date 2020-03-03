defmodule ApiExample.Repo do
  use Ecto.Repo,
    otp_app: :api_example,
    adapter: Ecto.Adapters.Postgres
end
