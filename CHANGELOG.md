# Changelog for [Hybrid API](https://github.com/hybridlabs/hybrid-api)

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

- Updated to Minecraft 1.21.11
- Updated to Minecraft 26.1.2
- Updated to Minecraft 26.2
- Updated to Minecraft 26.3

<details>
<summary>Developer notes</summary>

- Features now carry their configuration directly; `ConfiguredFeature` and `FeatureConfiguration`
  were removed, and custom feature types register a `MapCodec` in `FEATURE_TYPE`
- `PoseStack.mulPose` only accepts matrices and transformations, so axis-angle rotations need
  wrapping in a `Matrix4f`
- Swing state is tracked inside `LivingEntity`; `swingTime`, `attackAnim` and `updateSwingTime`
  are gone, and attacks use `swingForAttack`
- Vanilla's `TryFindWaterGoal` was removed, so a replacement ships in HAPI

- GeckoLib 5.5 uses the `com.geckolib` package
- Held-item layers follow the new item render-state API, so models are resolved into
  `ItemStackRenderState`s instead of raw stacks
- Random patch worldgen was reimplemented, since vanilla 26.1 removed the feature in favour of
  placement modifiers
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
