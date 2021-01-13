FROM centos:centos8

RUN yum update -y \
  && yum install -y java-11-openjdk-devel java-11-openjdk zip unzip which \
  && curl -s "https://get.sdkman.io" | bash \
  && source "$HOME/.sdkman/bin/sdkman-init.sh" \
  && sdk install scala 2.13.4 \
  && sdk install sbt \
  && mkdir -p ./app

ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk
ENV PATH $PATH:/root/.sdkman/candidates/sbt/current/bin
ADD ./ ./app

WORKDIR ./app
ENTRYPOINT ["sbt", "run"]
