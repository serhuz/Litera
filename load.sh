#!/usr/bin/env bash

KEY_PROPS=${HOME}/litera/key.properties
export KEY_PROPS

if [ ! -f "$KEY_PROPS" ]; then

  echo "Generation key.properties file"
  touch "${KEY_PROPS}"

  {
    echo "debugKeyAlias=$DBG_KEY_ALIAS"
    echo "debugKeyPassword=$DBG_KEY_PASS"
    echo "debugStoreFile=$DBG_KEY_FILE"
    echo "debugStorePassword=$DBG_KEY_FILE_PASS"
    echo "releaseKeyAlias=$REL_KEY_ALIAS"
    echo "releaseKeyPassword=$REL_KEY_PASS"
    echo "releaseStoreFile=$REL_KEY_FILE"
    echo "releaseStorePassword=$REL_KEY_FILE_PASS"
  } >> "${KEY_PROPS}"

  echo "Done"

fi
