const fs = require("fs");
const util = require("util");
const child_process = require("child_process");
// 调用util.promisify方法，返回一个promise,如const { stdout, stderr } = await exec('rm -rf build')
const exec = util.promisify(child_process.exec);
const path = require("path");
const srcPath = path.join(__dirname, "src");

let pathObj = {};

/**
 * 拷贝文件夹下的文件
 * @param filePath 文件路径
 */
function copyDirFile(filePath) {
    //根据文件路径读取文件，返回文件列表
    fs.readdir(filePath, function (err, files) {
        if (err) {
            console.warn(err);
            throw err;
            return;
        } else {
            //遍历读取到的文件列表
            files.forEach(function (fileName) {
                //获取当前文件的绝对路径
                const filedir = path.join(filePath, fileName);
                //根据文件路径获取文件信息，返回一个fs.Stats对象
                fs.stat(filedir, function (eror, stats) {
                    if (eror) {
                        console.warn("copyDirFile 获取文件stats失败");
                        throw eror;
                        return;
                    } else {
                        const isFile = stats.isFile(); //是文件
                        const isDir = stats.isDirectory(); //是文件夹
                        if (isFile) {
                            const _dirName = path.dirname(filedir);
                            const _fileName = path.basename(filedir);
                            const fileNameArray = _fileName.split(".");
                            const filePrefix = fileNameArray[0];
                            const fileSuffix = `.${fileNameArray[1]}`;
                            // 排除掉 .DS_Store 这个文件
                            if (fileSuffix !== ".DS_Store") {
                                const destPath = `${_dirName}/${filePrefix}Copy${fileSuffix}`;
                                fs.copyFile(filedir, destPath, (err) => {});
                                pathObj[filedir] = destPath;
                            }
                        }
                        if (isDir) {
                            copyDirFile(filedir); //递归，如果是文件夹，就继续遍历该文件夹下面的文件
                        }
                    }
                });
            });
        }
    });
}


/**
 * 更新文件夹下的文件
 * @param filePath 文件路径
 */
function updateDirFile(filePath) {
    //根据文件路径读取文件，返回文件列表
    fs.readdir(filePath, function (err, files) {
        if (err) {
            console.warn(err);
        } else {
            //遍历读取到的文件列表
            files.forEach(function (fileName) {
                //获取当前文件的绝对路径
                const filedir = path.join(filePath, fileName);
                //根据文件路径获取文件信息，返回一个fs.Stats对象
                fs.stat(filedir, function (eror, stats) {
                    if (eror) {
                        console.warn("updateDirFile 获取文件stats失败");
                    } else {
                        const isFile = stats.isFile(); //是文件
                        const isDir = stats.isDirectory(); //是文件夹
                        if (isFile) {
                            _fileName = path.basename(filedir);
                            const fileNameArray = _fileName.split(".");
                            fileSuffix = `.${fileNameArray[1]}`;
                            let splitStr;
                            if (fileSuffix === ".java") {
                                splitStr = "/** The above part of the comment is auto generated, the following part is written by the user, please do not delete this comment. */";
                            } else if (fileSuffix === ".xml") {
                                splitStr = "<!-- The above part of the comment is auto generated, the following part is written by the user, please do not delete this comment. -->";
                            } else {
                                splitStr = "The above part of the comment is auto generated, the following part is written by the user, please do not delete this comment.";
                            }
                            if (pathObj[filedir] && fs.existsSync(pathObj[filedir])) {
                                let data1 = fs.readFileSync(filedir, "utf-8");
                                let str1 = data1.toString();
                                const _data1 = str1.split(splitStr);
                                updateFileStr = _data1[0];

                                let data2 = fs.readFileSync(pathObj[filedir], "utf-8");
                                let str2 = data2.toString();
                                const _data2 = str2.split(splitStr);
                                copyFileStr = _data2[1];
                                resultStr = updateFileStr + splitStr + copyFileStr;
                                // 写入文件
                                fs.writeFile(filedir, resultStr, function (err) {
                                    if (err) return console.log(err);
                                });
                            }
                        }
                        if (isDir) {
                            updateDirFile(filedir); //递归，如果是文件夹，就继续遍历该文件夹下面的文件
                        }
                    }
                });
            });
        }
    });
}

/**
 * 删除文件
 * @param delPath 文件路径
 */
function deleteFile(delPath) {
    try {
        // 判断文件或文件夹是否存在
        if (fs.existsSync(delPath)) {
            fs.unlinkSync(delPath);
        } else {
            console.log("inexistence path：", delPath);
        }
    } catch (error) {
        console.log("del error", error);
    }
}

/**
 * 删除文件夹下的文件
 * @param filePath 文件路径
 */
function deleteDirFile(filePath) {
    //根据文件路径读取文件，返回文件列表
    fs.readdir(filePath, function (err, files) {
        if (err) {
            console.warn(err);
        } else {
            //遍历读取到的文件列表
            files.forEach(function (fileName) {
                //获取当前文件的绝对路径
                const filedir = path.join(filePath, fileName);
                //根据文件路径获取文件信息，返回一个fs.Stats对象
                fs.stat(filedir, function (eror, stats) {
                    if (eror) {
                        console.warn("deleteDirFile 获取文件stats失败");
                    } else {
                        const isFile = stats.isFile(); //是文件
                        const isDir = stats.isDirectory(); //是文件夹
                        if (isFile) {
                            if (pathObj[filedir] && fs.existsSync(pathObj[filedir])) {
                                deleteFile(pathObj[filedir]);
                            }
                        }
                        if (isDir) {
                            deleteDirFile(filedir); //递归，如果是文件夹，就继续遍历该文件夹下面的文件
                        }
                    }
                });
            });
        }
    });
}


function copyDirFileFun(srcPath) {
    return new Promise((resolve, reject) => {
        try {
            copyDirFile(srcPath);
        } catch (error) {
            reject(error);
        }
        resolve();
        console.log('copyDirFileFun complete')
    });
}

function deleteDirFileFun(srcPath) {
    return new Promise((resolve, reject) => {
        try {
            deleteDirFile(srcPath);
        } catch (error) {
            reject(error);
        }
        resolve();
        console.log('deleteDirFileFun complete')
    });
}

function updateDirFileFun(srcPath) {
    return new Promise((resolve, reject) => {
        try {
            updateDirFile(srcPath);
        } catch (error) {
            reject(error);
        }
        resolve();
        console.log('updateDirFileFun complete')
    });
}

async function init() {
    try {
        // 创建文件副本
        await copyDirFileFun(srcPath);

        // 生成mybatis相关文件
        await exec("bash ./jasmine-bin/bin/jasmine ./jasmine.properties");
        console.log('jasmine-bin complete')

        // 更新文件
        updateDirFileFun(srcPath).then(() => {
                // 删除文件副本
                deleteDirFileFun(srcPath);
            }
        );
    } catch (error) {
        console.log("init function run error: " + error);
    }
}

init();
