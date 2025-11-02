#1.1 latest version of Ubuntu
FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y --no-install-recommends
RUN apt-get -y install software-properties-common

#1.2 java 8 package 
RUN apt-get update && apt-get install -y openjdk-8-jdk
RUN apt-get -y install libjansi1-java

#1.3 install python
RUN add-apt-repository ppa:deadsnakes/ppa 
RUN apt install python3

#1.5 install vim, curl 
RUN apt-get -y install vim curl wget
RUN apt-get update


RUN curl -fL https://github.com/VirtusLab/coursier-m1/releases/latest/download/cs-aarch64-pc-linux.gz | gzip -d > cs
ENV PATH="/root/.local/share/coursier/bin:${PATH}"

# run non-interactively
RUN chmod +x cs && ./cs setup --yes
RUN apt-get update

# install sbt
RUN apt-get install apt-transport-https curl gnupg -yqq
RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | tee /etc/apt/sources.list.d/sbt.list
RUN echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | tee /etc/apt/sources.list.d/sbt_old.list
RUN curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | gpg --no-default-keyring --keyring gnupg-ring:/etc/apt/trusted.gpg.d/scalasbt-release.gpg --import
RUN chmod 644 /etc/apt/trusted.gpg.d/scalasbt-release.gpg
RUN apt-get update
RUN apt-get -y install sbt
