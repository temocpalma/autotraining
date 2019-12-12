defmodule ApiExampleWeb.PageController do
  use ApiExampleWeb, :controller

  def index(conn, _params) do
    render(conn, "index.html")
  end
end
