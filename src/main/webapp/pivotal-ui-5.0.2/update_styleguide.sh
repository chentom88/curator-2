#!/usr/bin/env bash

set -ex

pushd library
  gulp build
popd

pushd styleguide
  bundle
  rm -rf node_modules/pui-*
  npm i --no-progress
popd

