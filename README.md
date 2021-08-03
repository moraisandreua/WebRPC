# WebRPC

Send a request with the command and the configurable token.
The command will execute in the server.
Be careful, anyone with the token can manage your server.
Set your own accessToken.

## Installation:
Just put the .jar file into plugins folder
Run the server once
Change the default access token in config.yml
You're ready.

## Commands:

/webrpc - show the token

## Permissions:

- webrpc.accessToken - allows the usage of previous command

## Example:

GET 127.0.0.1:4567/?accessToken=password&command=say Hello World

## My use case:

Online marketplace, where I need to give custom items to customer running commands on server. It was needed to be autonomous.

## Why I created:

I didn't find any similar plugins dated after 2015.

ps: using Ajax API or Fetch API (javascript) are not recommended since token is exposed. Server side scripts solve that security issue. And feel free to fork this project and increase the security or create your own mappings.
