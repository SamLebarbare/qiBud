# qiBud - Collaboration Hub Fundation
<pre>
             ,-. 
    ,     ,-.   ,-. 
   / \   (   )-(   ) 
   \ |  ,.>-(   )-< 
    \|,' (   )-(   ) 
     Y ___`-'   `-' 
     |/__/   `-' 
     | 
     | 
     |    -qiBud- 
  ___|_____________ 
</pre>


## Pre-Arch
<pre>


 +----------------------------+                                      +----------------------------+
 |project-buds module         |                                      |qibud-server                |
 |   +------------------------|-+                                    |----------------------------|
 |   |base-buds module        | |        +----------------------+    |                            |
 |   |------------------------|-|        | core-client module   |    |                            |
 |   |                        | |        |----------------------|    |                            |
 |   |                        |          |                      |    | provide buds               |
 |   |                        |                                 +------------->                   |
 |   |                        |          |register              |    |                            |
 |   |                        | +--------------->               |    |                            |
 |   |                        | |        |                      |    |                            |
 |   |                        | |        +----------------------+    |                            |
 +----------------------------+ |                                    +----------------------------+
     |                          |                                    | play! 1.2.X                |
     +-----^--------------------+                                    |                            |
           |                                                         +----------------------------+
           |
           |            +----------------------------------------------+
           |            | bud-storage module                           |
           |            |----------------------------------------------|
           |read/write  |                                              |
           +------------>                                              |
                        |                                              |
                        +----------------------------------------------+
</pre>
