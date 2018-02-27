Запуск бенчмарков производился с ключами:

`java -jar target/microbenchmarks.jar -f 1 -i 10 -wi 10 -wf 1 -rf json`

| Framework     |Context start cold (s/op)| Context start warm (ops/s) | Get bean cold (s/op) | Get bean warm (ops/s) |
|---|---|---|---|---|
| **Guice** | 4.484376E-4 | 105991.47 | 2.86266E-5 |  1.185377E7
| **Spring Annotations** | 0.016739 |   150.66588 | 1.81242E-5 | 1.86042E7
| **Spring Java** | 0.0089460345 |   464.92239 | 1.294591E-5 | 2.01126E7
| **Spring XML** | 0.0224459893 | 56.949831 |  1.16331E-5 |  1.73754E7

Подробная информация о проведенных тестах в директориях соответствующих модулей в `jmh-result.json`