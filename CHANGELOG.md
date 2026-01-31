# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

[1.1.2] - 2026-01-31

### Fixed

- native image build startup error

## [1.1.1] - 2026-01-31

### Changed 

- JVM base image registry.access.redhat.com/ubi9/openjdk-21:1.24-2.1769595322

## [1.1.0] - 2026-01-29

### Changed

- update quarkus to version 3.31.1
- mongo db version 8.2.3

## [1.0.2] - 2025-02-17

### Added

- docker compose and compose native

### Fixed

- build react on native docker workflow

[1.0.1] - 2025-02-17

### Changed

- workflow review
- using actions/setup-java for native build
- fj-core version set to 8.6.6
- quarkus version set to 3.18.3
- lombok version set to 1.18.34
- switched to mongodb container tag 8.0

## [1.0.0] - 2024-06-29

### Added

- quarkus photobook project
- native image optimization instruction
- benchmark scripts
- docker build workflows
- Google App Engine (GAE) deployment note
- KNative deployment note