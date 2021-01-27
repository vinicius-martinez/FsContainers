# Containers & Kubernetes

Neste repositório estarão disponíveis nosso *Workshop* de implementação fazendo uso de [Docker](https://www.docker.com/) e [Kubernetes](https://kubernetes.io/)

## Pré Requisitos

- [Docker Desktop Win/Mac 3.x](https://www.docker.com/products/docker-desktop)

## Workshop

0. [Docker - Info](#workshop-docker-info)
1. [Docker - Kernel](#workshop-docker-kernel)
2. [Docker - Hostname](#workshop-docker-hostname)
3. [Docker - Recursos](#workshop-docker-resource)
4. [Docker - Detalhes](#workshop-docker-details)
5. [Docker - Acesso "Remoto"](#workshop-docker-remote)
6. [Docker - Accesso Externo](#workshop-docker-externalaccess)
7. [Docker - Persistência](#workshop-docker-persistence)
8. [Docker - Criando Imagens - Commit](#workshop-docker-imagecreation-commit)
9. [Docker - Criando Imagens - DockerFile](#workshop-docker-imagecreation-dockerfile)
10. [Docker - Tag](#workshop-docker-tag)
11. [Docker - Registro de Containers](#workshop-docker-registry)

## Implementação

### 0 - Docker - Info <a name="workshop-docker-info">

  Sintaxe: `docker info`

  ```
  Client:
   Context:    default
   Debug Mode: false
   Plugins:
    app: Docker App (Docker Inc., v0.9.1-beta3)
    buildx: Build with BuildKit (Docker Inc., v0.5.1-docker)
    scan: Docker Scan (Docker Inc., v0.5.0)

  Server:
   Containers: 119
    Running: 1
    Paused: 0
    Stopped: 118
   Images: 8
   Server Version: 20.10.2
   Storage Driver: overlay2
    Backing Filesystem: extfs
    Supports d_type: true
    Native Overlay Diff: true
   Logging Driver: json-file
   Cgroup Driver: cgroupfs
   Cgroup Version: 1
   Plugins:
    Volume: local
    Network: bridge host ipvlan macvlan null overlay
    Log: awslogs fluentd gcplogs gelf journald json-file local logentries splunk syslog
   Swarm: inactive
   Runtimes: io.containerd.runc.v2 io.containerd.runtime.v1.linux runc
   Default Runtime: runc
   Init Binary: docker-init
   containerd version: 269548fa27e0089a8b8278fc4fc781d7f65a939b
   runc version: ff819c7e9184c13b7c2607fe6c30ae19403a7aff
   init version: de40ad0
   Security Options:
    seccomp
     Profile: default
   Kernel Version: 4.19.121-linuxkit
   Operating System: Docker Desktop
   OSType: linux
   Architecture: x86_64
   CPUs: 6
   Total Memory: 9.735GiB
   Name: docker-desktop
   ID: NV62:2TBN:BWIB:OPEH:J6PP:LOMY:VWAS:VTHW:5R3E:DWGP:RD3V:47H4
   Docker Root Dir: /var/lib/docker
   Debug Mode: true
    File Descriptors: 40
    Goroutines: 45
    System Time: 2021-01-27T15:31:03.3458533Z
    EventsListeners: 3
   HTTP Proxy: gateway.docker.internal:3128
   HTTPS Proxy: gateway.docker.internal:3129
   Registry: https://index.docker.io/v1/
   Labels:
   Experimental: false
   Insecure Registries:
    127.0.0.0/8
   Live Restore Enabled: false
  ```
  * o *output* deve variar variar ligeiramente do apresentando anteriormente observando as características do seu ambiente

### 1 - Docker - Kernel <a name="workshop-docker-kernel">

  Sintaxe: `docker run -it alpine`
  ```
  / # uname -a
  Linux 407aedb085f1 4.19.121-linuxkit #1 SMP Tue Dec 1 17:50:32 UTC 2020 x86_64 Linux
  ```

  Sintaxe: `docker run -it centos bash`
  ```
  [root@fe3303373936 /]# uname -a
  Linux fe3303373936 4.19.121-linuxkit #1 SMP Tue Dec 1 17:50:32 UTC 2020 x86_64 x86_64 x86_64 GNU/Linux
  ```

  Sintaxe: `docker run -it fedora bash`
  ```
  [root@7bda5c8d4338 /]# uname -a
  Linux 7bda5c8d4338 4.19.121-linuxkit #1 SMP Tue Dec 1 17:50:32 UTC 2020 x86_64 x86_64 x86_64 GNU/Linux
  ```

  Sintaxe: `docker run -it debian bash`
  ```
  root@dfb6141efd67:/# uname -a
  Linux dfb6141efd67 4.19.121-linuxkit #1 SMP Tue Dec 1 17:50:32 UTC 2020 x86_64 GNU/Linux
  ```

### 2 - Docker - Hostname <a name="workshop-docker-hostname">

  Sintaxe: `for i in `seq 3`; do docker run -d busybox /bin/sh -c "while true; do echo Hello from Linux container [\$HOSTNAME];sleep $i;done"; done`
  ```
  0fe0b438f83a41dd8b296895362d31d70dc5f518384082482b428ad499b3dcbb
  78b5decfc3d6e172e049f4e6404ae9ed2980b355aaa03190c87e69610f73862e
  4b081ec8c88b5395f29c31cd7fd98b6832d36b783ff996745a57d1c5574d64cb
  ```

### 3 - Docker - Recursos <a name="workshop-docker-resource">

  Sintaxe: `docker stats`
  ```
  CONTAINER ID   NAME                   CPU %     MEM USAGE / LIMIT   MEM %     NET I/O       BLOCK I/O   PIDS
  4b081ec8c88b   wonderful_dijkstra     0.00%     468KiB / 9.735GiB   0.00%     1.01kB / 0B   0B / 0B     2
  78b5decfc3d6   clever_curran          0.10%     532KiB / 9.735GiB   0.01%     1.01kB / 0B   0B / 0B     2
  0fe0b438f83a   priceless_kowalevski   0.07%     564KiB / 9.735GiB   0.01%     1.01kB / 0B   0B / 0B     2
  39e9f1a02d42   pedantic_ardinghelli   0.07%     580KiB / 9.735GiB   0.01%     1.01kB / 0B   0B / 0B     2
  a9218409c709   upbeat_visvesvaraya    0.00%     476KiB / 9.735GiB   0.00%     1.1kB / 0B    0B / 0B     2
  9efcbed40973   agitated_snyder        0.10%     616KiB / 9.735GiB   0.01%     1.19kB / 0B   0B / 0B     2
  ```

  Sintaxe: `docker top $CONTAINER ID`
  ```
  UID                 PID                 PPID                C                   STIME               TTY                 TIME                CMD
  root                3258                3232                0                   18:09               ?                   00:00:00            /bin/sh -c while true; do echo Hello from Linux container [$HOSTNAME];sleep 3;done
  root                7515                3258                0                   18:28               ?                   00:00:00            sleep 3
  ```

  Sintaxe: `docker stats $CONTAINER ID`
  ```
  CONTAINER ID   NAME                 CPU %     MEM USAGE / LIMIT   MEM %     NET I/O       BLOCK I/O   PIDS
  4b081ec8c88b   wonderful_dijkstra   0.00%     500KiB / 9.735GiB   0.00%     1.08kB / 0B   0B / 0B     2
  ```
