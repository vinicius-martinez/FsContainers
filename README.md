# Containers

Neste repositório estarão disponíveis nosso *Workshop* de implementação fazendo uso da tecnologia [Docker](https://www.docker.com/) & [Docker Compose](https://docs.docker.com/compose/)

## Pré Requisitos

- [Docker Desktop Win/Mac 3.x](https://www.docker.com/products/docker-desktop)

## Workshop

0. [Docker - Info](#workshop-docker-info)
1. [Docker - Kernel](#workshop-docker-kernel)
2. [Docker - Hostname](#workshop-docker-hostname)
3. [Docker - Recursos](#workshop-docker-resource)
4. [Docker - Detalhes](#workshop-docker-details)
5. [Docker - Executando Containers](#workshop-docker-running)
6. [Docker - Acesso "Remoto"](#workshop-docker-remote)
7. [Docker - Accesso Externo](#workshop-docker-externalaccess)
8. [Docker - Persistência](#workshop-docker-persistence)
9. [Docker - Criando Imagens - Commit](#workshop-docker-imagecreation-commit)
10. [Docker - Criando Imagens - DockerFile](#workshop-docker-imagecreation-dockerfile)
11. [Docker - Tag](#workshop-docker-tag)
12. [Docker - Registro de Containers](#workshop-docker-registry)

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

  * o *output* pode variar variar ligeiramente do apresentando anteriormente observando as características do seu ambiente e/ou versão da imagem

### 2 - Docker - Hostname <a name="workshop-docker-hostname">

  Sintaxe: `for i in `seq 3`; do docker run -d busybox /bin/sh -c "while true; do echo Hello from Linux container [\$HOSTNAME];sleep $i;done"; done`
  ```
  0fe0b438f83a41dd8b296895362d31d70dc5f518384082482b428ad499b3dcbb
  78b5decfc3d6e172e049f4e6404ae9ed2980b355aaa03190c87e69610f73862e
  4b081ec8c88b5395f29c31cd7fd98b6832d36b783ff996745a57d1c5574d64cb
  ```
  * o *output* pode variar variar ligeiramente do apresentando anteriormente observando as características do seu ambiente e/ou versão da imagem

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

  Sintaxe: `docker top $CONTAINER_ID`
  ```
  UID                 PID                 PPID                C                   STIME               TTY                 TIME                CMD
  root                3258                3232                0                   18:09               ?                   00:00:00            /bin/sh -c while true; do echo Hello from Linux container [$HOSTNAME];sleep 3;done
  root                7515                3258                0                   18:28               ?                   00:00:00            sleep 3
  ```

  Sintaxe: `docker stats $CONTAINER_ID`
  ```
  CONTAINER ID   NAME                 CPU %     MEM USAGE / LIMIT   MEM %     NET I/O       BLOCK I/O   PIDS
  4b081ec8c88b   wonderful_dijkstra   0.00%     500KiB / 9.735GiB   0.00%     1.08kB / 0B   0B / 0B     2
  ```

  * o *output* pode variar variar ligeiramente do apresentando anteriormente observando as características do seu ambiente e/ou versão da imagem

### 4 - Docker - Detalhes <a name="workshop-docker-details">

  Sintaxe: `docker inspect $CONTAINER_ID`
  ```
  [
    {
        "Id": "225241332ebb10752c1e47257020b137372d11054dba40172fcfbb3ab0c275d2",
        "Created": "2021-01-27T19:11:24.9247276Z",
        "Path": "bash",
        "Args": [],
        "State": {
            "Status": "running",
            "Running": true,
            "Paused": false,
            "Restarting": false,
            "OOMKilled": false,
            "Dead": false,
            "Pid": 9071,
            "ExitCode": 0,
            "Error": "",
            "StartedAt": "2021-01-27T19:11:25.2920129Z",
            "FinishedAt": "0001-01-01T00:00:00Z"
        },
        "Image": "sha256:e7d08cddf791fe3245267654331eb21b805458b3412d368018009355855044a3",
        "ResolvConfPath": "/var/lib/docker/containers/225241332ebb10752c1e47257020b137372d11054dba40172fcfbb3ab0c275d2/resolv.conf",
        "HostnamePath": "/var/lib/docker/containers/225241332ebb10752c1e47257020b137372d11054dba40172fcfbb3ab0c275d2/hostname",
        "HostsPath": "/var/lib/docker/containers/225241332ebb10752c1e47257020b137372d11054dba40172fcfbb3ab0c275d2/hosts",
        "LogPath": "/var/lib/docker/containers/225241332ebb10752c1e47257020b137372d11054dba40172fcfbb3ab0c275d2/225241332ebb10752c1e47257020b137372d11054dba40172fcfbb3ab0c275d2-json.log",
        "Name": "/gallant_knuth",
        "RestartCount": 0,
        "Driver": "overlay2",
        "Platform": "linux",
        "MountLabel": "",
        "ProcessLabel": "",
        "AppArmorProfile": "",
        "ExecIDs": null,
        "HostConfig": {
            "Binds": null,
            "ContainerIDFile": "",
            "LogConfig": {
                "Type": "json-file",
                "Config": {}
            },
            "NetworkMode": "default",
            "PortBindings": {},
            "RestartPolicy": {
                "Name": "no",
                "MaximumRetryCount": 0
            },
            "AutoRemove": false,
            "VolumeDriver": "",
            "VolumesFrom": null,
            "CapAdd": null,
            "CapDrop": null,
            "CgroupnsMode": "host",
            "Dns": [],
            "DnsOptions": [],
            "DnsSearch": [],
            "ExtraHosts": null,
            "GroupAdd": null,
            "IpcMode": "private",
            "Cgroup": "",
            "Links": null,
            "OomScoreAdj": 0,
            "PidMode": "",
            "Privileged": false,
            "PublishAllPorts": false,
            "ReadonlyRootfs": false,
            "SecurityOpt": null,
            "UTSMode": "",
            "UsernsMode": "",
            "ShmSize": 67108864,
            "Runtime": "runc",
            "ConsoleSize": [
                0,
                0
            ],
            "Isolation": "",
            "CpuShares": 0,
            "Memory": 0,
            "NanoCpus": 0,
            "CgroupParent": "",
            "BlkioWeight": 0,
            "BlkioWeightDevice": [],
            "BlkioDeviceReadBps": null,
            "BlkioDeviceWriteBps": null,
            "BlkioDeviceReadIOps": null,
            "BlkioDeviceWriteIOps": null,
            "CpuPeriod": 0,
            "CpuQuota": 0,
            "CpuRealtimePeriod": 0,
            "CpuRealtimeRuntime": 0,
            "CpusetCpus": "",
            "CpusetMems": "",
            "Devices": [],
            "DeviceCgroupRules": null,
            "DeviceRequests": null,
            "KernelMemory": 0,
            "KernelMemoryTCP": 0,
            "MemoryReservation": 0,
            "MemorySwap": 0,
            "MemorySwappiness": null,
            "OomKillDisable": false,
            "PidsLimit": null,
            "Ulimits": null,
            "CpuCount": 0,
            "CpuPercent": 0,
            "IOMaximumIOps": 0,
            "IOMaximumBandwidth": 0,
            "MaskedPaths": [
                "/proc/asound",
                "/proc/acpi",
                "/proc/kcore",
                "/proc/keys",
                "/proc/latency_stats",
                "/proc/timer_list",
                "/proc/timer_stats",
                "/proc/sched_debug",
                "/proc/scsi",
                "/sys/firmware"
            ],
            "ReadonlyPaths": [
                "/proc/bus",
                "/proc/fs",
                "/proc/irq",
                "/proc/sys",
                "/proc/sysrq-trigger"
            ]
        },
        "GraphDriver": {
            "Data": {
                "LowerDir": "/var/lib/docker/overlay2/5ed0260a1084405546ae1e381c804f3feaa81fbc198774ff711b7141e2be6f04-init/diff:/var/lib/docker/overlay2/008b9adee20e15c7545094a5fb25542dbb9c18ec4bc6f22332d65dce91b5a64c/diff",
                "MergedDir": "/var/lib/docker/overlay2/5ed0260a1084405546ae1e381c804f3feaa81fbc198774ff711b7141e2be6f04/merged",
                "UpperDir": "/var/lib/docker/overlay2/5ed0260a1084405546ae1e381c804f3feaa81fbc198774ff711b7141e2be6f04/diff",
                "WorkDir": "/var/lib/docker/overlay2/5ed0260a1084405546ae1e381c804f3feaa81fbc198774ff711b7141e2be6f04/work"
            },
            "Name": "overlay2"
        },
        "Mounts": [],
        "Config": {
            "Hostname": "225241332ebb",
            "Domainname": "",
            "User": "",
            "AttachStdin": true,
            "AttachStdout": true,
            "AttachStderr": true,
            "Tty": true,
            "OpenStdin": true,
            "StdinOnce": true,
            "Env": [
                "PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
            ],
            "Cmd": [
                "bash"
            ],
            "Image": "debian",
            "Volumes": null,
            "WorkingDir": "",
            "Entrypoint": null,
            "OnBuild": null,
            "Labels": {}
        },
        "NetworkSettings": {
            "Bridge": "",
            "SandboxID": "478efa7ab6a58743ff477548bf6e31e159635f56c9daf4cfa6df5f2205b0e875",
            "HairpinMode": false,
            "LinkLocalIPv6Address": "",
            "LinkLocalIPv6PrefixLen": 0,
            "Ports": {},
            "SandboxKey": "/var/run/docker/netns/478efa7ab6a5",
            "SecondaryIPAddresses": null,
            "SecondaryIPv6Addresses": null,
            "EndpointID": "bd19c90e375158d676b9a6f008171880b3476f8d72da6450ac6d7ce703247c82",
            "Gateway": "172.17.0.1",
            "GlobalIPv6Address": "",
            "GlobalIPv6PrefixLen": 0,
            "IPAddress": "172.17.0.2",
            "IPPrefixLen": 16,
            "IPv6Gateway": "",
            "MacAddress": "02:42:ac:11:00:02",
            "Networks": {
                "bridge": {
                    "IPAMConfig": null,
                    "Links": null,
                    "Aliases": null,
                    "NetworkID": "f26c4e5a49182e1034db912d4bf3505ff4f8f01a3d9b22ad7d13da0c8bf3d0d6",
                    "EndpointID": "bd19c90e375158d676b9a6f008171880b3476f8d72da6450ac6d7ce703247c82",
                    "Gateway": "172.17.0.1",
                    "IPAddress": "172.17.0.2",
                    "IPPrefixLen": 16,
                    "IPv6Gateway": "",
                    "GlobalIPv6Address": "",
                    "GlobalIPv6PrefixLen": 0,
                    "MacAddress": "02:42:ac:11:00:02",
                    "DriverOpts": null
                  }
              }
          }
      }
  ]
  ```
  * o *output* pode variar variar ligeiramente do apresentando anteriormente observando as características do seu ambiente e/ou versão da imagem

### 5 - Docker - Executando Containers <a name="workshop-docker-running">

  Sintaxe: `docker run -it viniciusmartinez/echo-hostname:1.0`
  ```
  HOSTNAME: e1bb8d1a88f2
  HOSTNAME: e1bb8d1a88f2
  HOSTNAME: e1bb8d1a88f2
  HOSTNAME: e1bb8d1a88f2
  HOSTNAME: e1bb8d1a88f2
  HOSTNAME: e1bb8d1a88f2
  ```

  Sintaxe: `docker run -d viniciusmartinez/echo-hostname:1.0`
  ```
  249a8345b2f9bab8c47de21cd9de4ba6ef8d155e467b86f1675799c97e02b772
  ```

  Sintaxe: `docker ps`
  ```
  CONTAINER ID   IMAGE                                COMMAND               CREATED              STATUS              PORTS     NAMES
  249a8345b2f9   viniciusmartinez/echo-hostname:1.0   "sh /echo_hostname"   About a minute ago   Up About a minute             priceless_elion
  ```

  Sintaxe: `docker logs $CONTAINER_ID`
  ```
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  ```

  Sintaxe: `docker pause $CONTAINER_ID`
  ```
  sem output no log
  ```

  Sintaxe: `docker unpause $CONTAINER_ID`
  ```
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  ```

  Sintaxe: `docker unpause $CONTAINER_ID`
  ```
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  HOSTNAME: 249a8345b2f9
  ```

  Sintaxe: `docker stop $CONTAINER_ID`
  ```
  249a8345b2f9
  ```

  Sintaxe:
  ```
  docker run -it viniciusmartinez/echo-hostname:1.0

  docker ps
  CONTAINER ID   IMAGE                                COMMAND               CREATED         STATUS         PORTS     NAMES
  27fe9eed1b7c   viniciusmartinez/echo-hostname:1.0   "sh /echo_hostname"   5 seconds ago   Up 5 seconds             serene_pasteur
  ```

  Sintaxe: `docker kill 27fe9eed1b7c`
  ```
  27fe9eed1b7c
  ```

  Sintaxe:
  ```
  docker run -it viniciusmartinez/echo-hostname:1.0

  docker container stop $(docker container ls -aq)
  ```

  Sintaxe:
  ```
  docker container rm $(docker container ls -aq)
  docker rmi $(docker images -a -q) --force
  ```
