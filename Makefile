SHELL := /bin/bash

VERSION := $(shell sbt 'export version' | tail -n 1)
export VERSION

default: build

.PHONY: version
version:
	@echo $(VERSION)

.PHONY: clean
clean:
	sbt clean

.PHONY: build
build: clean
	sbt assembly
