require recipes-kernel/linux/linux.inc
inherit gettext

SECTION = "kernel"
SUMMARY = "Android kernel for the Asus Zenwatch"
HOMEPAGE = "https://android.googlesource.com/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"
COMPATIBLE_MACHINE = "anthias"

SRC_URI = "git://android.googlesource.com/kernel/msm;branch=android-msm-anthias-3.10-marshmallow-dr1-wear-release;protocol=https \
    file://defconfig \
    file://img_info \
    file://0001-Create-copy-of-devfreq_trace.h.patch \
    file://0001-msm-mdss-mdp-Don-t-use-tracing-features.patch \
    file://0001-psmouse-base-disable-references-to-lifebook_detect-w.patch \
    file://0001-static-inline-in-ARM-ftrace.h.patch \
    file://0001-traps-only-use-unwind_backtrace-if-available.patch"
SRCREV = "5d054632429188226b8c1e1e545475c89ad4c582"
LINUX_VERSION ?= "3.10"
PV = "${LINUX_VERSION}+marshmallow"
S = "${WORKDIR}/git"
B = "${S}"

# Removes some headers that are installed incorrectly

do_configure_prepend() {
    # Fixes build with GCC5
    echo "#include <linux/compiler-gcc4.h>" > ${S}/include/linux/compiler-gcc5.h
    echo "#include <linux/compiler-gcc5.h>" > ${S}/include/linux/compiler-gcc6.h
}

do_install_append() {
    rm -rf ${D}/usr/src/usr/
}

BOOT_PARTITION = "/dev/mmcblk0p11"

inherit mkboot