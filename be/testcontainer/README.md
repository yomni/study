# Getting Started

### Requirement for Test (with MacOS)
- [comlima](https://golang.testcontainers.org/system_requirements/using_colima/)
- docker
- docker-compose

```shell
# requirement installation
brew install colima
```

```shell
# requirement installation
brew install docker
```

```shell
# requirement installation
brew install docker-compose
```


### Issues

```shell
	Caused by: java.lang.IllegalStateException: Previous attempts to find a Docker environment failed. Will not retry. Please see logs and check configuration
```

- 원인 : docker.sock 경로를 알 수 없어서 발생
- 해결 : 심볼릭 링크로 docker.sock 를 colima 의 docker.sock 과 연결

```shell
sudo ln -sf $HOME/.colima/default/docker.sock /var/run/docker.sock
```
