require recipes-kernel/linux/linux-yocto.inc

SECTION = "kernel"
SUMMARY = "linux-next with patches for Samsung smartwatches"
HOMEPAGE = "https://kernel.org"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
COMPATIBLE_MACHINE = "rinato"

SRC_URI = " \
    git://git@github.com/casept/linux-samsung-smartwatch.git;protocol=https;branch=rinato \
"
SRC_URI[sha256sum] = "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"

SRCREV = "cdce41c4a6c6fd71eaa067d97115e8450dae81dc"

LINUX_VERSION ?= "6.10-rc5"

PV = "${LINUX_VERSION}"
S = "${WORKDIR}/git"
B = "${S}"

KBUILD_DEFCONFIG:rinato ?= "rinato_debug_defconfig"
KCONFIG_MODE = "alldefconfig"
KERNEL_IMAGETYPE = "zImage"
KERNEL_ARTIFACT_NAME = "zImage"

# For some reason, the kernel artifact gets a weird name that Yocto itself doesn't recognize
do_install:append() {
        cp "${D}/${KERNEL_IMAGEDEST}/zImage-6.10.0-rc5-next-20240627-yocto-standard-gcdce41c4a6c6" "${D}/${KERNEL_IMAGEDEST}/zImage"
}

KERNEL_DEVICETREE = "samsung/exynos3250-rinato.dtb"
KERNEL_DEVICETREE_BUNDLE = "1"
