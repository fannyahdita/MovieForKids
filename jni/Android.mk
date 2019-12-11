TOP_LOCAL_PATH:=$(call my-dir)
include $(call all-subdir-makefiles)
LOCAL_PATH := $(TOP_LOCAL_PATH)

include $(CLEAR_VARS)
LOCAL_MODULE := movieforkids
LOCAL_SRC_FILES := movieforkids.c
include $(BUILD_SHARED_LIBRARY)