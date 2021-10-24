# Кряк для продуктов Atlassian

- JIRA Software
- JIRA Service Desk (Jira Service Management)
- Confluence
- Bitbucket
- Crowd

## Способ установки

1. Идём в README.md нужной версии и повторяем действия из раздела Dockerfile (копируем указанные .jar файлы в указанные папки в контейнере)
2. Копируем Server ID сервера нужного продукта и заменяем его в соответствующем license_key_product.txt файле
3. Генерим ключ командой: php atlassian-keygen.php -e license_key_product.txt
4. Copy key generate in console
5. Скопированный ключ используем для активации продукта

(!) В каждом README есть раздел Способ установки. Он может отличаться от основного способа

## Создание собственного файла лицензии для приложения из маркета (для Jira/Confluence)

1. Заходим в список установленных приложений и идём к окну ввода Ключа лицензии (License key).
2. Рядом видим Ключ приложения (App key), например `com.gliffy.integration.jira`
3. Копируем его
4. Копируем файл лицензии от confluence/jira и заменяем 4 значения (+ Server ID, если он другой)

На примере Jira:

```text
com.gliffy.integration.jira.active=true
com.gliffy.integration.jira.LicenseTypeName=COMMERCIAL
com.gliffy.integration.jira.NumberOfClusterNodes=0
com.gliffy.integration.jira.NumberOfUsers=-1
```

Для Confluence ключ будет `com.gliffy.integration.confluence`

Пример можно посмотреть в файле `license_key_gliffy_jira.txt` или `license_key_gliffy_confluence.txt`

Дальше полученную лицензию можно использовать для генерации ключа (желательно закоммитить файл лицензии, чтобы в след раз не надо было делать то же самое)

Имя файла называем следующим образом:

```text
license_key_${app_name}_${jira|confluence}.txt
```

## Полезные ссылки

[Установка Service Desk в JIRA](https://community.atlassian.com/t5/Jira-questions/How-do-I-add-Jira-ServiceDesk-to-my-existing-Jira-Software/qaq-p/293122)

[Установка и активация Atlassian Confluence 6.3.4](https://ealebed.github.io/posts/2017/%D1%83%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0-%D0%B8-%D0%B0%D0%BA%D1%82%D0%B8%D0%B2%D0%B0%D1%86%D0%B8%D1%8F-atlassian-confluence-6.3.4/)

[Установка и активация JIRA Software Server 7.5.0](https://ealebed.github.io/posts/2017/%D1%83%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0-%D0%B8-%D0%B0%D0%BA%D1%82%D0%B8%D0%B2%D0%B0%D1%86%D0%B8%D1%8F-jira-software-server-7.5.0/)

[benzfield/bitbucket-crack](https://github.com/benzfield/bitbucket-crack)

[binhnt-teko/jira-crack](https://github.com/binhnt-teko/jira-crack)

[hgqapp/atlassian-agent](https://github.com/hgqapp/atlassian-agent)
