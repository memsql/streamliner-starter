SHELL := /bin/bash

##############################
# BUILD
#
VERSION := $(shell sbt 'export version' | tail -n 1)
export VERSION

.PHONY: version
version:
	@echo $(VERSION)

.PHONY: clean
clean:
	sbt clean

.PHONY: build
build: clean
	sbt assembly
