# Changelog for [Hybrid API](https://github.com/hybridlabs/hybrid-api)

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

- Birds spawn during world generation like vanilla animals (registered in the CREATURE spawn group) and then stay there
- Leashed, tamed, bred and creature-net birds are no longer despawned by distance check
  (leashed birds now count as persistent)

## [1.1.3] 2026-10-04
- Added SeekSurfaceGoal
- Made animals persistent after being fed or bred (hopefully)

## [1.1.2] 2026-09-23

- Fixed small aquatic mobs, such as the flying fish, becoming invisible at close range
