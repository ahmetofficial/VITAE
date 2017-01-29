/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('GROUPS_POSTS', {
    group_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'GROUPS',
        key: 'group_id'
      }
    },
    post_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'USER_POSTS',
        key: 'post_id'
      }
    }
  }, {
    tableName: 'GROUPS_POSTS'
  });
};
