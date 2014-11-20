/**
 * Created by adam on 2014/11/8.
 */

function createFold(event) {
    $('#createFoldModal').modal();
}

function doCreate(event){
    newName = $('#createFoldModal #createFold_newName').val();
    alert(newName)
    alert('ajax远程调用')
}

function renameFold(event) {

    $('#renameFoldModal').modal();
}

function doRename(event){
    newName = $('#renameFoldModal #renameFold_newName').val();
    alert(newName);
    alert('ajax远程调用');
}

function deleteFold(event) {
    $('#deleteFoldModal').modal();
}

function doDelete(event){
    alert('ajax远程调用');
}

function wrapChildNode(childList){
    for(var o in childList){
        alert(o.text)
        o.icon = "glyphicon glyphicon-tag";
        o.isPrivateChild = true;
    }

    return childList;
}

function getEmptyChildNode(strText,strId){
    var node = {
        text: strText,
        icon: "glyphicon glyphicon-tag",
        isPrivateChild: true,
        priId: strId
    };

    return node;
}

function buildTreeData(childList) {
    var defautlTree = [
        {
            text: '写信',
            icon: 'glyphicon glyphicon-pencil',
            tagId:1
        },
        {
            text: "收信箱",
            icon: "glyphicon glyphicon-envelope",
            href: "1",
            badge: 3,
            tagId:2
        },
        {
            text: "已发送",
            icon: "glyphicon glyphicon-share-alt",
            tagId:3
        },
        {
            text: "自定义文件夹",
            icon: "glyphicon glyphicon-tags",
            isPrivateParent: true,
            nodes: null
        },
        {
            text: "已删除",
            icon: "glyphicon glyphicon-trash",
            tagId:4
        },
        {
            text: "垃圾邮件",
            icon: "glyphicon glyphicon-ban-circle",
            tagId:5
        }
    ];

    defautlTree[3].nodes = childList;
    return defautlTree;
}

function buildContractData(){
    var contracts = [
        {
            text:"已知联系人",
            icon:"glyphicon glyphicon-star",
            tagId:1
        },
        {
            text:'未知联系人',
            icon:"glyphicon glyphicon-star-empty",
            tagId:2
        }
    ];

    return contracts;
}
