defmodule ID3Parser do

  def parse(file_name) do
    case File.read(file_name) do
      {:ok, mp3} ->
        IO.inspect mp3
        mp3_byte_size = (byte_size(mp3) - 128)
        << _ :: binary-size(mp3_byte_size), id3_tag :: binary >> = mp3
        << tag :: binary-size(3), 
            title :: binary-size(30),
            artist :: binary-size(30),
            album :: binary-size(30),
            year :: binary-size(4),
            comment :: binary-size(30),
            _rest :: binary >> = id3_tag
        IO.puts "#{tag} => #{artist} - #{title}/#{album}, #{year}"
      _ ->
        IO.puts "Couldn't open #{file_name}"
    end
  end
end
