# Кряк для Atlassian Crowd (< 3.6.2) (на самом деле < 3.0.0 или типа того)

Dockerfile:

```dockerfile
FROM atlassian/crowd:3.0.0-ubuntu

COPY atlassian-extras-3.2.jar /opt/atlassian/crowd/crowd-webapp/WEB-INF/lib
COPY atlassian-extras-decoder-v2-3.1.1.jar /opt/atlassian/crowd/crowd-webapp/WEB-INF/lib
COPY atlassian-extras-legacy-3.3.0.jar /opt/atlassian/crowd/crowd-webapp/WEB-INF/lib
```

## Проверенные образы Crowd

--

## Способ установки

Как везде
