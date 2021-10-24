# Кряк для Atlassian Bitbucket (< 7.4.2) (на самом деле < 6 или типа того)

Old bitbucket versions:
6.4.0-ubuntu 7.3-ubuntu 7.4.0-ubuntu 5.9.1

Dockerfile:

```dockerfile
FROM atlassian/bitbucket-server:6.0.0

COPY atlassian-extras-3.2.jar /opt/atlassian/bitbucket/app/WEB-INF/lib
COPY atlassian-extras-decoder-v2-3.1.1.jar /opt/atlassian/bitbucket/app/WEB-INF/lib
COPY atlassian-extras-legacy-3.3.0.jar /opt/atlassian/bitbucket/app/WEB-INF/lib
```

## Проверенные образы Bitbucket

--

## Способ установки

Как везде
