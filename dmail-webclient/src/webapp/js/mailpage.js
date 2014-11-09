/**
 * Created by adam on 2014/11/8.
 */

function createFold(event) {
    //alert('create Fold');
    $('#createFoldModal').modal({});
}

function renameFold(event) {
    $('#renameFoldModal').modal();
}

function deleteFold(event) {
    //alert('delete Fold');
    $('#deleteFoldModal').modal();
}

function wrapChildNode(childList){
    for(var o in childList){
        alert(o.text)
        o.icon = "glyphicon glyphicon-tag";
        o.isPrivateChild = true;
    }

    return childList;
}

function buildTreeData(childList) {
    var defautlTree = [
        {
            text: '写信',
            icon: 'glyphicon glyphicon-pencil'
        },
        {
            text: "收信箱",
            icon: "glyphicon glyphicon-envelope",
            href: "1",
            badge: 3
        },
        {
            text: "已发送",
            icon: "glyphicon glyphicon-share-alt"
        },
        {
            text: "自定义文件夹",
            icon: "glyphicon glyphicon-tags",
            isPrivateParent: true,
            nodes: null
        },
        {
            text: "已删除",
            icon: "glyphicon glyphicon-trash"
        },
        {
            text: "垃圾邮件",
            icon: "glyphicon glyphicon-refresh"
        }
    ];

    var newNodes = wrapChildNode(childList);


    defautlTree[3].nodes = newNodes;
    return defautlTree;
}