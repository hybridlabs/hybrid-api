# Changelog for [Hybrid API](https://github.com/hybridlabs/hybrid-api)

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.3.0] Unreleased

- Updated to Minecraft 1.21.11
- Aquatic and flying animals keep their animations and variants
- Birds spawn during world generation like vanilla animals, and leashed, tamed, bred and
  creature-net birds no longer despawn from the distance check
- Animals that breathe underwater no longer drown

<details>
<summary>Developer notes</summary>

- GeckoLib 5 support in the base models and renderers, using render-state data tickets instead of
  reading the animatable during rendering
- Models can adjust bones through the new `HABoneAdjustingModel` hook
- Entity variant, UUID and water-animal moistness are exposed to models through data tickets
- Instruments were removed, since vanilla no longer has a built-in instrument registry

</details>

## [1.1.3] 2026-10-04

- Added SeekSurfaceGoal
- Made animals persistent after being fed or bred (hopefully)

## [1.1.2] 2026-09-23

- Fixed small aquatic mobs, such as the flying fish, becoming invisible at close range
