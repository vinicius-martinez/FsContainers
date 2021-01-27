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
