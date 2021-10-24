# Кряк для Atlassian Confluence (>= 7.0.1)

Dockerfile:

```dockerfile
FROM cptactionhank/atlassian-confluence:7.0.1

COPY atlassian-extras-decoder-v2-3.4.1.jar /opt/atlassian/confluence/confluence/WEB-INF/lib
COPY atlassian-universal-plugin-manager-plugin-4.0.6.jar /opt/atlassian/confluence/confluence/WEB-INF/atlassian-bundled-plugins
```

## Проверенные образы Confluence

- cptactionhank/atlassian-confluence:7.0.1

## Способ установки

(!!!) .jar файлы надо брать из той же папки, а не из корня (!!!)

Когда проверял - ловил баги с установкой плагинов, поэтому лучше обновить конфу до 7.5.0 и использовать нужный кряк
