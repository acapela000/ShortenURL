Installing docker mongodb image and connecting database

# Installing docker

First, can not open Docker due to kernel
<picture>

Reference link:
https://learn.microsoft.com/en-us/windows/wsl/install-manual#step-4---download-the-linux-kernel-update-package

https://learn.microsoft.com/en-us/windows/wsl/install#check-which-version-of-wsl-you-are-running


# Connecting database
Reference link: https://www.mongodb.com/docs/manual/tutorial/install-mongodb-community-with-docker/

use cmd:
docker run --name mongo -d mongodb/mongodb-community-server:latest

In that command I am not only pulling the image but also running a container.
to pull the image I can use "docker pull mongodb/mongodb-community-server:latest"

the creation of the container is good but I miss some things:

1. we need to open the port with -p 27017:27017
2. we need to set up env variables to change the user and password with -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret
3. We need to set up the volume to store the information outside the container: -v /my/custom:/etc/mongo

so let's make that. also maybe instead of using the comunity-server image we can use the default one: mongo:latest
The one you used is perfect but the community is not the official one (docker pull mongo) better to use official images just in case "https://hub.docker.com/_/mongo"

``` 
docker pull mongodb/mongo:latest -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret -v /my/custom:/etc/mongo
unknown shorthand flag: 'p' in -p
```

The error is still appear then I check with chatGPT bc I cant find where is the problem. So the error is about the syntax of cmd and I change it to this and then it works.
```
docker run -d -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret -v /my/custom:/etc/mongo --name mongodb mongo:latest
```

```
PS C:\Users\thaot\ShortenURL> dc
Usage:  docker pull [OPTIONS] NAME[:TAG|@DIGEST]

Download an image from a registry

Aliases:
docker image pull, docker pull

ec4c7cb44e89: Pull complete
38c7016c15b5: Pull complete
2d740b6c9811: Pull complete
329604f22078: Pull complete
27fa33e0d643: Pull complete
a84daea1c560: Pull complete
Digest: sha256:8b12b02cacb6b88178a526f2cfdf2429fb79c6b8b1b1649e893299519a462cdf
Status: Downloaded newer image for mongo:latest
682f3912778d06f2bba77f43dc0bd9261d9b4df13065eae5f8977a9a918d1551
```

Check the amount of images are redundant then removing it

Now there is a problem with the volume, see you are using "/my/custom" right?


Let's create a folder called db for example.
So I created a folder in the root of the project called /db
this is where we are going to link the volume of the db and we don't need that folder in github
that's why I add it in .gitignore so no matter what we never push it to git.

Now let's remove the container and create again with that folder
```
docker run -d -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret -v .\db:/etc/mongo --name mongodb mongo:latest
```

Now it should work, let's read the logs from docker:

docker logs mongodb

docker logs -f mongodb (-f before the name of the container) sorry it was -f instead -w :)
what is that :D :))  Jajajaj this are the logs from the database. when you execute springboot you see some lines related to springboot
the libraries or the connection with the database right? this is the same but for the database. it give us
information about the status of the database (now looks like everything is working fine so let's stop watching the logs
you can go to the terminal anc press ctrl+c)

