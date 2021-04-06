log4j2-benchmarks
=================
Benchmarks to check the speed of Log4J 2's various available logging mechanisms: concatination, formatting, and lambdas.

# Results
See the classes themselves for exact implementation details.

Results taken under WSL running Linux 5.4.91, on a Intel(R) Core(TM) i7-9750H CPU @ 2.60GHz with 12GB of RAM maximum.

## LoggingBench
Overall comparision of the various styles, with various data types.

```java
Benchmark                                                                         Mode  Cnt       Score       Error   Units
LoggingBench.concatGuarded_disabled_float                                         thrpt    5  241149.780 ± 14761.736  ops/ms
LoggingBench.concatGuarded_disabled_float:·gc.alloc.rate.norm                     thrpt    5      ≈ 10⁻⁶                B/op
LoggingBench.concatGuarded_disabled_float:·gc.count                               thrpt    5         ≈ 0              counts
LoggingBench.concatGuarded_disabled_int                                           thrpt    5  294904.937 ±  9791.444  ops/ms
LoggingBench.concatGuarded_disabled_int:·gc.alloc.rate.norm                       thrpt    5      ≈ 10⁻⁶                B/op
LoggingBench.concatGuarded_disabled_int:·gc.count                                 thrpt    5         ≈ 0              counts
LoggingBench.concatGuarded_disabled_longString                                    thrpt    5   49623.941 ±  4221.891  ops/ms
LoggingBench.concatGuarded_disabled_longString:·gc.alloc.rate.norm                thrpt    5     216.009 ±     0.001    B/op
LoggingBench.concatGuarded_disabled_longString:·gc.count                          thrpt    5     179.000              counts
LoggingBench.concatGuarded_disabled_shortString                                   thrpt    5   81469.175 ±  1372.754  ops/ms
LoggingBench.concatGuarded_disabled_shortString:·gc.alloc.rate.norm               thrpt    5      48.003 ±     0.001    B/op
LoggingBench.concatGuarded_disabled_shortString:·gc.count                         thrpt    5     104.000              counts

LoggingBench.concatGuarded_enabled_float                                          thrpt    5    2991.610 ±    78.012  ops/ms
LoggingBench.concatGuarded_enabled_float:·gc.alloc.rate.norm                      thrpt    5     192.030 ±     0.008    B/op
LoggingBench.concatGuarded_enabled_float:·gc.count                                thrpt    5      34.000              counts
LoggingBench.concatGuarded_enabled_int                                            thrpt    5    3430.255 ±    17.458  ops/ms
LoggingBench.concatGuarded_enabled_int:·gc.alloc.rate.norm                        thrpt    5     144.028 ±     0.007    B/op
LoggingBench.concatGuarded_enabled_int:·gc.count                                  thrpt    5      36.000              counts
LoggingBench.concatGuarded_enabled_longString                                     thrpt    5    2328.708 ±   127.181  ops/ms
LoggingBench.concatGuarded_enabled_longString:·gc.alloc.rate.norm                 thrpt    5     888.087 ±     0.011    B/op
LoggingBench.concatGuarded_enabled_longString:·gc.count                           thrpt    5      77.000              counts
LoggingBench.concatGuarded_enabled_shortString                                    thrpt    5    3167.882 ±   158.977  ops/ms
LoggingBench.concatGuarded_enabled_shortString:·gc.alloc.rate.norm                thrpt    5     208.033 ±     0.002    B/op
LoggingBench.concatGuarded_enabled_shortString:·gc.count                          thrpt    5      40.000              counts

LoggingBench.concat_disabled_float                                                thrpt    5   20728.903 ±   719.436  ops/ms
LoggingBench.concat_disabled_float:·gc.alloc.rate.norm                            thrpt    5      64.007 ±     0.001    B/op
LoggingBench.concat_disabled_float:·gc.count                                      thrpt    5      54.000              counts
LoggingBench.concat_disabled_int                                                  thrpt    5   78098.780 ±  1335.797  ops/ms
LoggingBench.concat_disabled_int:·gc.alloc.rate.norm                              thrpt    5      32.002 ±     0.001    B/op
LoggingBench.concat_disabled_int:·gc.count                                        thrpt    5      73.000              counts
LoggingBench.concat_disabled_longString                                           thrpt    5   26959.660 ±  1290.710  ops/ms
LoggingBench.concat_disabled_longString:·gc.alloc.rate.norm                       thrpt    5     424.016 ±     0.001    B/op
LoggingBench.concat_disabled_longString:·gc.count                                 thrpt    5     171.000              counts
LoggingBench.concat_disabled_shortString                                          thrpt    5   41145.369 ±   731.641  ops/ms
LoggingBench.concat_disabled_shortString:·gc.alloc.rate.norm                      thrpt    5      80.006 ±     0.001    B/op
LoggingBench.concat_disabled_shortString:·gc.count                                thrpt    5     100.000              counts

LoggingBench.concat_enabled_float                                                 thrpt    5    2988.098 ±    85.673  ops/ms
LoggingBench.concat_enabled_float:·gc.alloc.rate.norm                             thrpt    5     192.037 ±     0.009    B/op
LoggingBench.concat_enabled_float:·gc.count                                       thrpt    5      42.000              counts
LoggingBench.concat_enabled_int                                                   thrpt    5    3455.287 ±    73.224  ops/ms
LoggingBench.concat_enabled_int:·gc.alloc.rate.norm                               thrpt    5     144.023 ±     0.001    B/op
LoggingBench.concat_enabled_int:·gc.count                                         thrpt    5      30.000              counts
LoggingBench.concat_enabled_longString                                            thrpt    5    2374.126 ±    52.729  ops/ms
LoggingBench.concat_enabled_longString:·gc.alloc.rate.norm                        thrpt    5     888.083 ±     0.002    B/op
LoggingBench.concat_enabled_longString:·gc.count                                  thrpt    5      75.000              counts
LoggingBench.concat_enabled_shortString                                           thrpt    5    3213.337 ±   231.779  ops/ms
LoggingBench.concat_enabled_shortString:·gc.alloc.rate.norm                       thrpt    5     208.033 ±     0.009    B/op
LoggingBench.concat_enabled_shortString:·gc.count                                 thrpt    5      40.000              counts

LoggingBench.implFormat_disabled_float                                            thrpt    5  242807.524 ±  5620.301  ops/ms
LoggingBench.implFormat_disabled_float:·gc.alloc.rate.norm                        thrpt    5      ≈ 10⁻⁶                B/op
LoggingBench.implFormat_disabled_float:·gc.count                                  thrpt    5         ≈ 0              counts
LoggingBench.implFormat_disabled_int                                              thrpt    5  211789.772 ± 11711.732  ops/ms
LoggingBench.implFormat_disabled_int:·gc.alloc.rate.norm                          thrpt    5      ≈ 10⁻⁶                B/op
LoggingBench.implFormat_disabled_int:·gc.count                                    thrpt    5         ≈ 0              counts
LoggingBench.implFormat_disabled_longString                                       thrpt    5   55402.545 ±   423.716  ops/ms
LoggingBench.implFormat_disabled_longString:·gc.alloc.rate.norm                   thrpt    5     216.008 ±     0.001    B/op
LoggingBench.implFormat_disabled_longString:·gc.count                             thrpt    5     173.000              counts
LoggingBench.implFormat_disabled_shortString                                      thrpt    5   80806.965 ±  5912.119  ops/ms
LoggingBench.implFormat_disabled_shortString:·gc.alloc.rate.norm                  thrpt    5      48.003 ±     0.001    B/op
LoggingBench.implFormat_disabled_shortString:·gc.count                            thrpt    5      95.000              counts

LoggingBench.implFormat_enabled_float                                             thrpt    5    2287.893 ±   145.612  ops/ms
LoggingBench.implFormat_enabled_float:·gc.alloc.rate.norm                         thrpt    5     120.029 ±     0.016    B/op
LoggingBench.implFormat_enabled_float:·gc.count                                   thrpt    5      24.000              counts
LoggingBench.implFormat_enabled_int                                               thrpt    5    2783.838 ±   359.273  ops/ms
LoggingBench.implFormat_enabled_int:·gc.alloc.rate.norm                           thrpt    5      88.021 ±     0.010    B/op
LoggingBench.implFormat_enabled_int:·gc.count                                     thrpt    5      21.000              counts
LoggingBench.implFormat_enabled_longString                                        thrpt    5    2145.168 ±   159.648  ops/ms
LoggingBench.implFormat_enabled_longString:·gc.alloc.rate.norm                    thrpt    5     656.070 ±     0.012    B/op
LoggingBench.implFormat_enabled_longString:·gc.count                              thrpt    5      57.000              counts
LoggingBench.implFormat_enabled_shortString                                       thrpt    5    2840.256 ±    75.724  ops/ms
LoggingBench.implFormat_enabled_shortString:·gc.alloc.rate.norm                   thrpt    5     152.029 ±     0.007    B/op
LoggingBench.implFormat_enabled_shortString:·gc.count                             thrpt    5      31.000              counts

LoggingBench.lambda_disabled_float                                                thrpt    5  242013.017 ±  6760.634  ops/ms
LoggingBench.lambda_disabled_float:·gc.alloc.rate.norm                            thrpt    5      ≈ 10⁻⁶                B/op
LoggingBench.lambda_disabled_float:·gc.count                                      thrpt    5         ≈ 0              counts
LoggingBench.lambda_disabled_int                                                  thrpt    5  312526.633 ± 13958.440  ops/ms
LoggingBench.lambda_disabled_int:·gc.alloc.rate.norm                              thrpt    5      ≈ 10⁻⁶                B/op
LoggingBench.lambda_disabled_int:·gc.count                                        thrpt    5         ≈ 0              counts
LoggingBench.lambda_disabled_longString                                           thrpt    5   50393.785 ±  7626.366  ops/ms
LoggingBench.lambda_disabled_longString:·gc.alloc.rate.norm                       thrpt    5     216.008 ±     0.001    B/op
LoggingBench.lambda_disabled_longString:·gc.count                                 thrpt    5     155.000              counts
LoggingBench.lambda_disabled_shortString                                          thrpt    5   80519.555 ±  2508.232  ops/ms
LoggingBench.lambda_disabled_shortString:·gc.alloc.rate.norm                      thrpt    5      48.003 ±     0.001    B/op
LoggingBench.lambda_disabled_shortString:·gc.count                                thrpt    5      95.000              counts

LoggingBench.lambda_enabled_float                                                 thrpt    5    2659.387 ±   185.334  ops/ms
LoggingBench.lambda_enabled_float:·gc.alloc.rate.norm                             thrpt    5     208.040 ±     0.003    B/op
LoggingBench.lambda_enabled_float:·gc.count                                       thrpt    5      40.000              counts
LoggingBench.lambda_enabled_int                                                   thrpt    5    2981.308 ±    96.586  ops/ms
LoggingBench.lambda_enabled_int:·gc.alloc.rate.norm                               thrpt    5     160.031 ±     0.001    B/op
LoggingBench.lambda_enabled_int:·gc.count                                         thrpt    5      35.000              counts
LoggingBench.lambda_enabled_longString                                            thrpt    5    2210.157 ±   126.117  ops/ms
LoggingBench.lambda_enabled_longString:·gc.alloc.rate.norm                        thrpt    5     904.094 ±     0.010    B/op
LoggingBench.lambda_enabled_longString:·gc.count                                  thrpt    5      79.000              counts
LoggingBench.lambda_enabled_shortString                                           thrpt    5    2949.438 ±    76.943  ops/ms
LoggingBench.lambda_enabled_shortString:·gc.alloc.rate.norm                       thrpt    5     224.036 ±     0.001    B/op
LoggingBench.lambda_enabled_shortString:·gc.count                                 thrpt    5      40.000              counts
```

## LargeLoggingBench
11 parameters, to force array allocations in `implFormat`.

```java
Benchmark                                                                Mode  Cnt       Score      Error   Units
LargeLoggingBench.implFormat_disabled                                   thrpt    5   35424.709 ± 3238.820  ops/ms
LargeLoggingBench.implFormat_disabled:·gc.alloc.rate.norm               thrpt    5     160.007 ±    0.001    B/op
LargeLoggingBench.implFormat_disabled:·gc.count                         thrpt    5      96.000             counts

LargeLoggingBench.implFormat_enabled                                    thrpt    5     900.082 ±   10.077  ops/ms
LargeLoggingBench.implFormat_enabled:·gc.alloc.rate.norm                thrpt    5     464.088 ±    0.001    B/op
LargeLoggingBench.implFormat_enabled:·gc.count                          thrpt    5      30.000             counts

LargeLoggingBench.lambda_disabled                                       thrpt    5  118509.684 ± 1396.925  ops/ms
LargeLoggingBench.lambda_disabled:·gc.alloc.rate.norm                   thrpt    5      ≈ 10⁻⁶               B/op
LargeLoggingBench.lambda_disabled:·gc.count                             thrpt    5         ≈ 0             counts

LargeLoggingBench.lambda_enabled                                        thrpt    5    1973.928 ±   69.410  ops/ms
LargeLoggingBench.lambda_enabled:·gc.alloc.rate.norm                    thrpt    5     416.066 ±    0.011    B/op
LargeLoggingBench.lambda_enabled:·gc.count                              thrpt    5      49.000             counts
```
