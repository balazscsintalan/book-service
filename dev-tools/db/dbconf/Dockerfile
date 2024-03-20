FROM mcr.microsoft.com/mssql/server:2019-latest

WORKDIR /usr/src/app

COPY --chown=10001 . /usr/src/app

ENV SA_PASSWORD Test@123
ENV ACCEPT_EULA Y
ENV MSSQL_PID Express

EXPOSE 1433

CMD /bin/bash ./entrypoint.sh